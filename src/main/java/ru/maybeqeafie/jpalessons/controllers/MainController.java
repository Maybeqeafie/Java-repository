package ru.maybeqeafie.jpalessons.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.maybeqeafie.jpalessons.Card;
import ru.maybeqeafie.jpalessons.CardRemote;
import ru.maybeqeafie.jpalessons.Entity.*;
import ru.maybeqeafie.jpalessons.repository.UsersReportRepository;
import ru.maybeqeafie.jpalessons.service.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
public class MainController {

    private final UsersService usersService;

    private final AccountsService accountsService;

    private final OperationService operationService;

    private final UsersReportService usersReportService;

    private final AdminCountService adminCountService;

    public Users userAction = new Users();
    public Users admin = new Users();
    Operation operation = new Operation();
    AdminCount adminCount = new AdminCount();

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public MainController(UsersService usersService, AccountsService accountsService, OperationService operationService, UsersReportService usersReportService,AdminCountService adminCountService) {
        this.usersService = usersService;
        this.accountsService = accountsService;
        this.operationService = operationService;
        this.usersReportService = usersReportService;
        this.adminCountService = adminCountService;
    }

    @RequestMapping(value = "/signin")
    public String getEntrance(Model model) {
        model.addAttribute("userLogin", new Users());
        return "entrance";
    }

    @RequestMapping(value = "/getRegister")
    public String getRegister(Model model) {
        model.addAttribute("userRegister", new Users());
        return "register";
    }


    @RequestMapping(value = "/getDonate")
    public String getDonate(Model model) {
        model.addAttribute("userCard", new Card());
        return "donate";
    }

    @RequestMapping(value = "/getSellAccount")
    public String getSellAccount(Model model) {
        model.addAttribute("sellAccount", new Accounts());
        model.addAttribute("user", userAction);
        return "account-form";
    }

    @PostMapping(value = "/entrance")
    public String entrance(@RequestParam("login") String login, @RequestParam("password") String password, Model model) {

        if (Objects.equals(usersService.getByLogin(login).getLogin(), login) &&
                Objects.equals(usersService.getByLogin(login).getPassword(), password)
        ) {

            admin.setLogin("mr_animals2");
            admin.setPassword("SpidnacknusBaf2");
            if (Objects.equals(usersService.getByLogin(login).getLogin(), admin.getLogin()) &&
                    Objects.equals(usersService.getByPassword(password).getPassword(), admin.getPassword())) {

                admin.setBalance(usersService.getByLogin(login).balance);
                admin.setId(usersService.getByLogin(login).id);

                model.addAttribute("users", admin);

                List<Operation> operationList = new ArrayList<>(operationService.getAll());
                model.addAttribute("operations", operationList);

                model.addAttribute("operationTrue", new Operation());
                model.addAttribute("operationFalse", new Operation());

                List<Users> usersManyReport = new ArrayList<>(usersService.getAll());
                model.addAttribute("userManyReport", usersManyReport);

                model.addAttribute("userBan", new Users());

                model.addAttribute("adminBalance", adminCountService.getById(1).getCount());

                userAction.setId(usersService.getByLogin(login).id);
                userAction.setLogin(login);
                userAction.setPassword(password);
                userAction.setBalance(usersService.getByLogin(login).balance);
                userAction.setIsBan(usersService.getByLogin(login).getIsBan());
                userAction.setCountReport(usersService.getByLogin(login).getCountReport());
                usersService.save(userAction);

                return "index-admin";
            }

            admin.setLogin("mr_animals2");
            admin.setPassword("SpidnacknusBaf2");
            admin.setBalance(usersService.getByLogin(admin.getLogin()).balance);
            admin.setId(usersService.getByLogin(admin.getLogin()).id);

            userAction.setId(usersService.getByLogin(login).id);
            userAction.setLogin(login);
            userAction.setPassword(password);
            userAction.setBalance(usersService.getByLogin(login).balance);
            userAction.setIsBan(usersService.getByLogin(login).getIsBan());
            userAction.setCountReport(usersService.getByLogin(login).getCountReport());
            userAction.setIsBanSales(usersService.getByLogin(login).getIsBanSales());
            usersService.save(userAction);
            model.addAttribute("users", userAction);

            List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
            model.addAttribute("accounts", accountsList);

            model.addAttribute("accountService", accountsService);

            model.addAttribute("accountDelete", new Accounts());

            model.addAttribute("accountReport", new Accounts());

            List<Operation> operationList = new ArrayList<>(operationService.getAll());
            model.addAttribute("operations", operationList);

            model.addAttribute("operationOk", new Operation());
            model.addAttribute("operationNotOk", new Operation());

            return "/index";
        }

        model.addAttribute("userLogin", new Users());

        return "/entrance";
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam("login") String login, @RequestParam("password") String password, Model model) throws NoSuchElementException {

        if (!Objects.equals(usersService.getByLogin(login).getLogin(), login)) {
            admin.setLogin("mr_animals2");
            admin.setPassword("SpidnacknusBaf2");
            admin.setBalance(usersService.getByLogin(admin.getLogin()).balance);
            admin.setId(usersService.getByLogin(admin.getLogin()).id);

            userAction.setId(usersService.getByLogin(login).id);
            userAction.setLogin(login);
            userAction.setPassword(password);
            userAction.setBalance(usersService.getByLogin(login).balance);
            userAction.setIsBan(usersService.getByLogin(login).getIsBan());
            userAction.setCountReport(usersService.getByLogin(login).getCountReport());
            userAction.setIsBanSales(usersService.getByLogin(login).getIsBanSales());
            usersService.save(userAction);
            model.addAttribute("users", userAction);

            List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
            model.addAttribute("accounts", accountsList);

            model.addAttribute("accountService", accountsService);

            model.addAttribute("accountDelete", new Accounts());

            model.addAttribute("accountReport", new Accounts());

            List<Operation> operationList = new ArrayList<>(operationService.getAll());
            model.addAttribute("operations", operationList);

            model.addAttribute("operationOk", new Operation());
            model.addAttribute("operationNotOk", new Operation());

            return "index";
        }

        model.addAttribute("user", usersService.getByLogin(login));
        model.addAttribute("userRegister", new Users());

        return "/register";
    }

    @PostMapping(value = "/donate")
    public String donate(@RequestParam("donate") int donate,
                         Model model) {
        if (donate <= 0) {
            return "donate";
        }

        List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
        model.addAttribute("accounts", accountsList);

        userAction.setBalance(userAction.balance + donate);
        usersService.save(userAction);
        model.addAttribute("users", userAction);

        model.addAttribute("accountService", accountsService);

        model.addAttribute("accountDelete", new Accounts());

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("accountReport", new Accounts());

        model.addAttribute("operationOk", new Operation());
        model.addAttribute("operationNotOk", new Operation());

        return "/index";
    }

    @PostMapping(value = "/sell")
    public String sell(@RequestParam("name") String name, @RequestParam("text") String text, @RequestParam("price") int price, @RequestParam("login") String login, @RequestParam("password") String password, Model model) {
        Accounts accounts = new Accounts();
        if (price <= 0) {
            return "/account-form";
        }

        accounts.setName(name);
        accounts.setText(text);
        accounts.setPrice(price);
        accounts.setLogin(login);
        accounts.setPassword(password);
        accounts.setUsers(usersService.getByLogin(userAction.login));
        accountsService.save(accounts);
        model.addAttribute("users", userAction);

        List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
        model.addAttribute("accounts", accountsList);

        model.addAttribute("accountService", accountsService);

        model.addAttribute("accountDelete", new Accounts());

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("accountReport", new Accounts());

        model.addAttribute("operationOk", new Operation());
        model.addAttribute("operationNotOk", new Operation());

        return "/index";
    }

    @PostMapping(value = "/delete")
    public String index(@RequestParam("id") int id, Model model) {
        model.addAttribute("users", userAction);

        accountsService.deleteById(id);

        List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
        model.addAttribute("accounts", accountsList);

        model.addAttribute("accountDelete", new Accounts());

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("accountReport", new Accounts());

        model.addAttribute("operationOk", new Operation());
        model.addAttribute("operationNotOk", new Operation());

        return "index";
    }

    @PostMapping(value = "/buy")
    public String buy(@RequestParam("id") int id, Model model) {

        if (userAction.getBalance() >= (int)(accountsService.getById(id).getPrice() * 1.05)) {

            userAction.setBalance(userAction.getBalance() - (int)(accountsService.getById(id).getPrice() * 1.05));
            usersService.save(userAction);

            admin.setBalance(
                    admin.getBalance() + accountsService.getById(id).getPrice()
            );
            usersService.save(admin);

            adminCount.setCount(adminCount.getCount() + (int)(accountsService.getById(id).getPrice() * 0.05));
            adminCount.setId(1);
            adminCountService.save(adminCount);

            Accounts accounts = new Accounts();
            accounts.setId(id);
            accounts.setLogin(accountsService.getById(id).getLogin());
            accounts.setPassword(accountsService.getById(id).getPassword());
            accounts.setPrice(accountsService.getById(id).getPrice());
            accounts.setName(accountsService.getById(id).getName());
            accounts.setText(accountsService.getById(id).getText());
            accounts.setUsers(accountsService.getById(id).getUsers());
            accounts.setBuyer(userAction);
            accounts.setIsBuy(true);
            accountsService.save(accounts);
//            try {
//                entityManager.getTransaction().begin();
//                entityManager.persist(userAction);
//                entityManager.persist(admin);
//                entityManager.persist(adminCount);
//                entityManager.getTransaction().commit();
//            } catch (Exception e) {
//                entityManager.getTransaction().rollback();
//            }

            model.addAttribute("accountDelete", new Accounts());

            model.addAttribute("users", userAction);

            model.addAttribute("login", accountsService.getById(id).login);
            model.addAttribute("password", accountsService.getById(id).password);

            model.addAttribute("operationOk", new Operation());
            model.addAttribute("operationNotOk", new Operation());

            model.addAttribute("idForPage", id);

            return "/buy-page";
        }

        model.addAttribute("accountDelete", new Accounts());

        model.addAttribute("users", userAction);

        List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
        model.addAttribute("accounts", accountsList);

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("accountReport", new Accounts());

        model.addAttribute("operationOk", new Operation());
        model.addAttribute("operationNotOk", new Operation());

        return "/index";
    }
//dsfsndkfgskdfkjshdjfhkjdsf
    @RequestMapping(value = "/index")
    public String index(Model model) {
        List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
        model.addAttribute("accounts", accountsList);

        model.addAttribute("users", userAction);

        model.addAttribute("accountDelete", new Accounts());

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("accountReport", new Accounts());

        model.addAttribute("operationOk", new Operation());
        model.addAttribute("operationNotOk", new Operation());

        return "/index";
    }

    @RequestMapping(value = "/indexAdmin")
    public String indexAdmin(Model model){
        model.addAttribute("users", admin);

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("operationTrue", new Operation());
        model.addAttribute("operationFalse", new Operation());

        List<Users> usersManyReport = new ArrayList<>(usersService.getAll());
        model.addAttribute("userManyReport", usersManyReport);

        model.addAttribute("userBan", new Users());

        model.addAttribute("adminBalance", adminCount.count);

        return "index-admin";
    }

    @PostMapping(value = "/ok")
    public String ok(@RequestParam("userOpinion") Boolean bool, @RequestParam("id") int id, Model model) {
//        operation.setId(id);
        operation.setName(accountsService.getById(id).name);
        operation.setText(accountsService.getById(id).text);
        operation.setPrice(accountsService.getById(id).price);
        operation.setLogin(accountsService.getById(id).login);
        operation.setPassword(accountsService.getById(id).password);
        operation.setUser(accountsService.getById(id).users);
        operation.setBuyer(userAction);
        operation.setAdminOpinion(null);

        admin.setBalance(admin.getBalance() - accountsService.getById(id).getPrice());
        usersService.save(admin);

        Users vendor = new Users();
        vendor.setLogin(accountsService.getById(id).getUsers().getLogin());
        vendor.setPassword(accountsService.getById(id).getUsers().getPassword());
        vendor.setId(accountsService.getById(id).getUsers().getId());
        vendor.setBalance(accountsService.getById(id).getUsers().getBalance() + accountsService.getById(id).getPrice());
        usersService.save(vendor);

        accountsService.delete(accountsService.getById(id));

        operation.setUserOpinion(bool);
        operationService.save(operation);

        List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
        model.addAttribute("accounts", accountsList);

        model.addAttribute("users", userAction);

        model.addAttribute("accountDelete", new Accounts());

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("accountReport", new Accounts());

        model.addAttribute("operationOk", new Operation());
        model.addAttribute("operationNotOk", new Operation());

        return "/index";
    }

    @PostMapping(value = "/report")
    public String report(@RequestParam("userOpinion") Boolean bool, @RequestParam("id") int id, Model model) {
//        operation.setId(id);
        operation.setName(accountsService.getById(id).name);
        operation.setText(accountsService.getById(id).text);
        operation.setPrice(accountsService.getById(id).price);
        operation.setLogin(accountsService.getById(id).login);
        operation.setPassword(accountsService.getById(id).password);
        operation.setUser(accountsService.getById(id).users);
        operation.setBuyer(userAction);
        operation.setAdminOpinion(null);

        accountsService.delete(accountsService.getById(id));

        operation.setUserOpinion(bool);
        operationService.save(operation);

        return "/report-page";
    }

    @PostMapping(value = "/backMoney")
    public String backMoney(@RequestParam("adminOpinion") Boolean bool, @RequestParam("id") int id, Model model) {
        operation.setAdminOpinion(bool);
        operation.setId(id);
        operation.setLogin(operationService.getById(id).getLogin());
        operation.setPassword(operationService.getById(id).getPassword());
        operation.setName(operationService.getById(id).getName());
        operation.setPrice(operationService.getById(id).getPrice());
        operation.setText(operationService.getById(id).getText());
        operation.setUserOpinion(operationService.getById(id).getUserOpinion());
        operation.setBuyer(operationService.getById(id).getBuyer());
        operation.setUser(operationService.getById(id).getUser());
        operationService.save(operation);

        admin.setBalance(admin.getBalance() - operation.getPrice());
        usersService.save(admin);

        Users buyer = new Users();
        buyer.setId(operationService.getById(id).getBuyer().getId());
        buyer.setLogin(operationService.getById(id).getBuyer().getLogin());
        buyer.setPassword(operationService.getById(id).getBuyer().getPassword());
        buyer.setBalance(operationService.getById(id).getBuyer().getBalance() + operation.getPrice());
        usersService.save(buyer);

        model.addAttribute("users", admin);

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("operationTrue", new Operation());
        model.addAttribute("operationFalse", new Operation());

        model.addAttribute("adminBalance", adminCount.count);

        return "/index-admin";
    }

    @PostMapping(value = "/reject")
    public String reject(@RequestParam("adminOpinion") Boolean bool, @RequestParam("id") int id, Model model) {
        operation.setAdminOpinion(bool);
        operation.setId(id);
        operation.setLogin(operationService.getById(id).getLogin());
        operation.setPassword(operationService.getById(id).getPassword());
        operation.setName(operationService.getById(id).getName());
        operation.setPrice(operationService.getById(id).getPrice());
        operation.setText(operationService.getById(id).getText());
        operation.setUserOpinion(operationService.getById(id).getUserOpinion());
        operation.setBuyer(operationService.getById(id).getBuyer());
        operation.setUser(operationService.getById(id).getUser());
        operationService.save(operation);

        admin.setBalance(admin.getBalance() - operation.getPrice());
        usersService.save(admin);

        Users vendor = new Users();
        vendor.setId(operationService.getById(id).getUser().getId());
        vendor.setLogin(operationService.getById(id).getUser().getLogin());
        vendor.setPassword(operationService.getById(id).getUser().getPassword());
        vendor.setBalance(operationService.getById(id).getUser().getBalance() + operation.getPrice());
        usersService.save(vendor);

        model.addAttribute("users", admin);

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("operationTrue", new Operation());
        model.addAttribute("operationFalse", new Operation());

        model.addAttribute("adminBalance", adminCount.count);

        return "/index-admin";
    }

    @PostMapping(value = "/reportHacked")
    public String reportHacked(@RequestParam("id") int id, Model model) {
        operation.setId(id);
        operation.setLogin(operationService.getById(id).getLogin());
        operation.setPassword(operationService.getById(id).getPassword());
        operation.setName(operationService.getById(id).getName());
        operation.setPrice(operationService.getById(id).getPrice());
        operation.setText(operationService.getById(id).getText());
        operation.setUserOpinion(operationService.getById(id).getUserOpinion());
        operation.setBuyer(operationService.getById(id).getBuyer());
        operation.setUser(operationService.getById(id).getUser());
        operation.setAdminOpinion(operationService.getById(id).getAdminOpinion());
        operation.getUser().setCountReport(operation.getUser().getCountReport() + 1);
        operation.setIsReported(true);

        if(operation.getUser().getCountReport() > 2){
            operation.getUser().setIsBanSales(true);
            usersService.save(operation.getUser());
        }

        operationService.save(operation);

        List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
        model.addAttribute("accounts", accountsList);

        model.addAttribute("users", userAction);

        model.addAttribute("accountDelete", new Accounts());

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("accountReport", new Accounts());

        model.addAttribute("operationOk", new Operation());
        model.addAttribute("operationNotOk", new Operation());

        return "/index";
    }

    @PostMapping(value = "/ban")
    public String ban(@RequestParam("id") int id,Model model){
        Users userBan = new Users();
        userBan.setId(id);
        userBan.setLogin(usersService.getById(id).getLogin());
        userBan.setPassword(usersService.getById(id).getPassword());
        userBan.setBalance(usersService.getById(id).getBalance());
        userBan.setCountReport(usersService.getById(id).getCountReport());
        userBan.setIsBan(true);
        usersService.save(userBan);

        model.addAttribute("users", admin);

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("operationTrue", new Operation());
        model.addAttribute("operationFalse", new Operation());

        List<Users> usersManyReport = new ArrayList<>(usersService.getAll());
        model.addAttribute("userManyReport", usersManyReport);

        model.addAttribute("userBan", new Users());

        model.addAttribute("adminBalance", adminCount.count);

        return"/index-admin";
    }

    @PostMapping(value = "/unBan")
    public String unBan(@RequestParam("id") int id,Model model){
        Users userBan = new Users();
        userBan.setId(id);
        userBan.setLogin(usersService.getById(id).getLogin());
        userBan.setPassword(usersService.getById(id).getPassword());
        userBan.setBalance(usersService.getById(id).getBalance());
        userBan.setCountReport(usersService.getById(id).getCountReport());
        userBan.setIsBan(null);
        usersService.save(userBan);

        model.addAttribute("users", admin);

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("operationTrue", new Operation());
        model.addAttribute("operationFalse", new Operation());

        List<Users> usersManyReport = new ArrayList<>(usersService.getAll());
        model.addAttribute("userManyReport", usersManyReport);

        model.addAttribute("userBan", new Users());

        model.addAttribute("adminBalance", adminCount.count);

        return"/index-admin";
    }

    @PostMapping(value = "/banSales")
    public String banSales(@RequestParam("id") int id,Model model){
        Users userBan = new Users();
        userBan.setId(id);
        userBan.setLogin(usersService.getById(id).getLogin());
        userBan.setPassword(usersService.getById(id).getPassword());
        userBan.setBalance(usersService.getById(id).getBalance());
        userBan.setCountReport(usersService.getById(id).getCountReport());
        userBan.setIsBan(usersService.getById(id).getIsBan());
        userBan.setIsBanSales(true);
        usersService.save(userBan);

        model.addAttribute("users", admin);

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("operationTrue", new Operation());
        model.addAttribute("operationFalse", new Operation());

        List<Users> usersManyReport = new ArrayList<>(usersService.getAll());
        model.addAttribute("userManyReport", usersManyReport);

        model.addAttribute("userBan", new Users());

        model.addAttribute("adminBalance", adminCount.count);

        return"/index-admin";
    }

    @PostMapping(value = "/unBanSales")
    public String unBanSales(@RequestParam("id") int id,Model model){
        Users userBan = new Users();
        userBan.setId(id);
        userBan.setLogin(usersService.getById(id).getLogin());
        userBan.setPassword(usersService.getById(id).getPassword());
        userBan.setBalance(usersService.getById(id).getBalance());
        userBan.setCountReport(0);
        userBan.setIsBan(usersService.getById(id).getIsBan());
        userBan.setIsBanSales(null);
        usersService.save(userBan);

        model.addAttribute("users", admin);

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("operationTrue", new Operation());
        model.addAttribute("operationFalse", new Operation());

        List<Users> usersManyReport = new ArrayList<>(usersService.getAll());
        model.addAttribute("userManyReport", usersManyReport);

        model.addAttribute("userBan", new Users());

        model.addAttribute("adminBalance", adminCount.count);

        return"/index-admin";
    }

    @RequestMapping(value = "/historySales")
    public String historySales(Model model){
        List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
        model.addAttribute("accounts", accountsList);

        model.addAttribute("users", userAction);

        model.addAttribute("accountDelete", new Accounts());

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("accountReport", new Accounts());
        return"/history-sales";
    }

    @RequestMapping(value = "/historyBuy")
    public String historyBuy(Model model){
        List<Accounts> accountsList = new ArrayList<>(accountsService.getAll());
        model.addAttribute("accounts", accountsList);

        model.addAttribute("users", userAction);

        model.addAttribute("accountDelete", new Accounts());

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("accountReport", new Accounts());
        return"/history-buy";
    }

    @RequestMapping(value = "/historyOperation")
    public String historyOperation(Model model){
        model.addAttribute("users", admin);

        List<Operation> operationList = new ArrayList<>(operationService.getAll());
        model.addAttribute("operations", operationList);

        model.addAttribute("operationTrue", new Operation());
        model.addAttribute("operationFalse", new Operation());

        List<Users> usersManyReport = new ArrayList<>(usersService.getAll());
        model.addAttribute("userManyReport", usersManyReport);

        model.addAttribute("userBan", new Users());
        return "/history-operation";
    }

    @RequestMapping(value = "/signOut")
    public String signOut(Model model) {
        operation = null;
        userAction = null;
        model.addAttribute("userLogin", new Users());

        return "/entrance";
    }

}
