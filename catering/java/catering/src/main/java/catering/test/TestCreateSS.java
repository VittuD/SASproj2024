package catering.test;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.assignment.Assignment;
import catering.businesslogic.event.Service;
import catering.businesslogic.menu.Menu;
import catering.businesslogic.menu.Section;
import catering.businesslogic.recipe.KitchenDuty;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.turn.KitchenTurn;
import catering.businesslogic.turn.Turn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestCreateSS {
    public static void main(String[] args) throws UseCaseLogicException {
        CatERing.getInstance().getUserManager().login("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

        Menu m = CatERing.getInstance().getMenuManager().getAllMenus().get(2);
        CatERing.getInstance().getMenuManager().setCurrentMenu(m);

        ArrayList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();

        CatERing.getInstance().getMenuManager().publish();
        System.out.println("\nMENU CREATED");
        System.out.println(m.testString());

        Service s = CatERing.getInstance().getEventManager().createService("Service 1");
        System.out.println("SERVICE CREATED: " + s.getName());
        s.setApprovedMenuId(m.getId());
        List<KitchenTurn> Kts = new ArrayList<>();
        LocalDate date = LocalDate.of(2024, 6, 6);
        LocalTime timeStart = LocalTime.of(9, 0);
        LocalTime timeEnd = LocalTime.of(17, 0);
        List<Assignment> assList = new ArrayList<>();
        assList.add(new Assignment.Builder().
                completed(false).
                description("description1").
                build());
        assList.add(new Assignment.Builder().
                completed(false).
                description("description2").
                build());
        KitchenTurn kt = new KitchenTurn(date, timeStart, timeEnd, assList, false, null, s, true, null);
        Kts.add(kt);
        s.setKitchenTurns(Kts);

        System.out.println("TEST CREATE SERVICE SUMMARY");
        CatERing.getInstance().getServiceSummaryMgr().create(s, kt);
        System.out.println("SERVICE SUMMARY CREATED "+CatERing.getInstance().getServiceSummaryMgr().showAssignmentsState(kt));
        CatERing.getInstance().getServiceSummaryMgr().addAssignment(recipes.get(0), kt, assList.get(0).getEstimatedTime(), assList.get(0).getCooks(), 1);
    }
}
