package entities;

import database.Simulator;
import enums.Category;
import enums.CityStrategyEnum;
import enums.ElvesType;
import fileio.AnnualChange;
import fileio.ChildOutput;
import fileio.ChildUpdate;
import fileio.ChildOutputList;
import fileio.GiftOutput;
import observable.IChildObserver;
import observable.IObservable;
import strategy.CityScoreStrategy;
import strategy.NiceScoreStrategy;
import strategy.OrderChildrenStrategy;

import java.util.ArrayList;
import java.util.Comparator;

import static common.Constants.ADULT_AGE;

public final class Santa implements IObservable {
    private static Santa instance = null;
    private double santaBudget;
    private double budgetUnit;
    private ArrayList<Gift> santaGiftsList;
    private ArrayList<Child> children;

    private Santa() {
    }

    public static Santa getInstance() {
        if (instance == null) {
            instance = new Santa();
        }
        return instance;
    }

    @Override
    public void add(final IChildObserver observer) {
        children.add((Child) observer);
    }

    @Override
    public void remove(final IChildObserver observer) {
        this.children.removeIf(c -> c.equals(observer));
    }

    @Override
    public void notifyObserver(final ArrayList<ChildUpdate> childrenUpdates) {
        for (Child c : this.children) {
                c.update(this, childrenUpdates);
        }
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public void setData(final double newSantaBudget, final double newBudgetUnit,
                        final ArrayList<Gift> newSantaGiftsList,
                        final ArrayList<Child> newChildren) {
        this.santaBudget = newSantaBudget;
        this.budgetUnit = newBudgetUnit;
        this.santaGiftsList = newSantaGiftsList;
        this.children = newChildren;
    }


    public void annualUpdate(final AnnualChange annualChange) {
        // Update budget
        Santa.getInstance().santaBudget = annualChange.getNewSantaBudget();

        // Add new gifts
        for (Gift g : annualChange.getNewGifts()) {
            Santa.getInstance().santaGiftsList.add(g);
        }

        // Remove all the adults in the current year (ages not updated yet)
        ArrayList<Child> adults = new ArrayList<>();
        for (Child c : this.children) {
            if (c.getAge() + 1 > ADULT_AGE) {
                adults.add(c);
            }
        }
        for (Child c : adults) {
            this.remove(c);
        }

        // Notify observers (children) about the change
        Santa.getInstance().notifyObserver(annualChange.getChildrenUpdates());

        // Add new children
        for (Child c : annualChange.getNewChildren()) {
            if (c.getAge() <= ADULT_AGE) {
                c.getNiceScoreHistory().add(c.getNiceScore());
                Santa.getInstance().add(c);
            }
        }

        // Update scores for all children
        Simulator.updateAverageScores(this.getChildren());

        // Recalculate budget unit
        double totalAverageScore = Simulator.calcTotalAverageScore(Santa.getInstance()
                .getChildren());

        // Set the new budget unit for the Santa instance
        this.budgetUnit = this.getSantaBudget() / totalAverageScore;


        // Check strategy
        if (annualChange.getStrategy().equals(CityStrategyEnum.NICE_SCORE)) {
            applyAssignStrategy(new NiceScoreStrategy());
        } else if (annualChange.getStrategy().equals(CityStrategyEnum.NICE_SCORE_CITY)) {
            applyAssignStrategy(new CityScoreStrategy());
        }
    }

    private void applyAssignStrategy(final OrderChildrenStrategy strategy) {
        strategy.orderChildren(this.children);
    }


    public ChildOutputList giveGifts(final ArrayList<Child> children) {

        ArrayList<ChildOutput> childOutputs = new ArrayList<>();

        for (Child currentChild : children) {
                double initialChildBudget = budgetUnit * currentChild.getAverageNiceScore();
                double newChildBudget = currentChild.calcElfTotalBudget(initialChildBudget);

                // Save the initial budget
                double assignedBudget = newChildBudget;
                ArrayList<GiftOutput> receivedGifts = new ArrayList<>();

                for (Category category : currentChild.getGiftsPreferences()) {
                    // Check the remaining budget
                    if((Double.compare(newChildBudget, 0) <= 0)) {
                        break;
                    }

                    Gift cheapestGift = getCheapestGiftByCategory(category);

                    // add the received gift to the list
                    if (cheapestGift != null
                            && Double.compare(newChildBudget, cheapestGift.getPrice()) > 0) {
                        receivedGifts.add(new GiftOutput(cheapestGift));
                        cheapestGift.setQuantity(cheapestGift.getQuantity() - 1);
                        newChildBudget -= cheapestGift.getPrice();
                    }
                }

            // If the child elf is yellow, try to assign a present if he didn't receive any present
            // yet from Santa
            if (currentChild.getElf().equals(ElvesType.YELLOW) && receivedGifts.size() == 0) {
                Gift cheapestGift = getFirstCheapestGiftByCategory(currentChild
                        .getGiftsPreferences().get(0));
                if (cheapestGift != null && cheapestGift.getQuantity() > 0) {
                    receivedGifts.add(new GiftOutput(cheapestGift));
                    cheapestGift.setQuantity(cheapestGift.getQuantity() - 1);
                }
            }

            // Create a new ChildOutput based on the current child and add it to the final
            // output list
            childOutputs.add(new ChildOutput(currentChild, assignedBudget, receivedGifts));
        }

        // Reset children array and output array
        this.getChildren().sort(Comparator.comparingInt(Child::getId));
        childOutputs.sort(Comparator.comparingInt(ChildOutput::getId));
        return new ChildOutputList(childOutputs);
    }

    private Gift getFirstCheapestGiftByCategory(final Category category) {
        // Deep copy
        ArrayList<Gift> giftListCopy = new ArrayList<>(santaGiftsList);

        // Sort the gift list by price
        giftListCopy.sort(Comparator.comparingDouble(Gift::getPrice));

        for (Gift gift : giftListCopy) {
            if (gift.getCategory().equals(category)) {
                return gift;
            }
        }
        return null;
    }

    private Gift getCheapestGiftByCategory(final Category category) {
        // Deep copy
        ArrayList<Gift> giftListCopy = new ArrayList<>(santaGiftsList);

        // Sort the gift list by price
        giftListCopy.sort(Comparator.comparingDouble(Gift::getPrice));

        for (Gift gift : giftListCopy) {
            if (gift.getCategory().equals(category) && gift.getQuantity() != 0) {
                return gift;
            }
        }
        return null;
    }

}
