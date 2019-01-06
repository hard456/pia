package cz.jpalcut.pia;

import cz.jpalcut.pia.dao.UserDAO;
import cz.jpalcut.pia.model.Role;
import cz.jpalcut.pia.model.State;
import cz.jpalcut.pia.model.User;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.RoleService;
import cz.jpalcut.pia.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Třída testující UserService
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceIntegrationTest {

    private UserDAO userDAO = mock(UserDAO.class);

    private AccountService accountService = mock(AccountService.class);

    private RoleService roleService = mock(RoleService.class);

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(accountService, roleService, userDAO);
    }

    /**
     * Testování návratu uživatele podle existujícího id
     */
    @Test
    public void getUserByIdExists() {
        User first = new User();
        first.setId(1);

        when(userDAO.findUserById(first.getId())).thenReturn(first);

        User second = userService.getUserById(first.getId());

        Assert.assertEquals(first, second);
    }

    /**
     * Testování návratu uživatele podle id, které neexistuje
     */
    @Test
    public void getUserByIdNull() {
        int userId = 1;
        User user = null;
        User userServiceReturn = null;

        when(userDAO.findUserById(userId)).thenReturn(null);

        userServiceReturn = userService.getUserById(userId);

        Assert.assertEquals(userServiceReturn, user);
    }

    /**
     * Testování správného uložení přihlašovacího jména a hesla do Spring Security uživatele
     */
    @Test
    public void loadUserByUsername() {
        User first = new User();
        first.setLoginId("12345678");
        first.setPin("12345");
        first.setFirstname("Karel");
        first.setLastname("Novák");

        when(userDAO.findUserByLoginId(first.getLoginId())).thenReturn(first);

        UserDetails second = userService.loadUserByUsername(first.getLoginId());

        Assert.assertEquals(second.getUsername(), first.getLoginId());
        Assert.assertEquals(second.getPassword(), first.getPin());
    }

    /**
     * Testování změny správných hodnot při editaci uživatele adminem
     */
    @Test
    public void editUserByAdmin() {
        State state = new State();
        state.setName("Česká Republika");

        //role list
        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        User newUser = new User();
        newUser.setSex("Muž");
        newUser.setTown("Písek");
        newUser.setState(state);
        newUser.setAddressNumber("44");
        newUser.setAddress("Zahradní");
        newUser.setEmail("test@test.cz");
        newUser.setZipCode("39701");
        newUser.setFirstname("Jan");
        newUser.setLastname("Novotný");

        User user = new User();
        user.setId(1);
        user.setPin("12345");
        user.setLoginId("1234578");
        user.setRoleList(roleList);

        //userService return
        User userServiceReturn;

        when(userDAO.save(any())).thenAnswer(i -> i.getArguments()[0]);

        userServiceReturn = userService.editUserByAdmin(user, newUser);

        //kontrola nezměnění nových údajů
        Assert.assertEquals(userServiceReturn.getState(), newUser.getState());
        Assert.assertEquals(userServiceReturn.getZipCode(), newUser.getZipCode());
        Assert.assertEquals(userServiceReturn.getTown(), newUser.getTown());
        Assert.assertEquals(userServiceReturn.getSex(), newUser.getSex());
        Assert.assertEquals(userServiceReturn.getEmail(), newUser.getEmail());
        Assert.assertEquals(userServiceReturn.getLastname(), newUser.getLastname());
        Assert.assertEquals(userServiceReturn.getFirstname(), newUser.getFirstname());
        Assert.assertEquals(userServiceReturn.getAddressNumber(), newUser.getAddressNumber());
        Assert.assertEquals(userServiceReturn.getAddress(), newUser.getAddress());

        //kontrola přepsání starých údajů
        Assert.assertEquals(userServiceReturn.getPin(), user.getPin());
        Assert.assertEquals(userServiceReturn.getLoginId(), user.getLoginId());
        Assert.assertEquals(userServiceReturn.getRoleList(), user.getRoleList());
        Assert.assertEquals(userServiceReturn.getId(), user.getId());

    }

    /**
     * Testování správného vytvoření uživatele
     */
    @Test
    public void addUser() {
        State state = new State();
        state.setName("Česká Republika");

        //role list
        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        User user = new User();
        user.setSex("Muž");
        user.setTown("Písek");
        user.setState(state);
        user.setAddressNumber("44");
        user.setAddress("Zahradní");
        user.setEmail("test@test.cz");
        user.setZipCode("39701");
        user.setFirstname("Jan");
        user.setLastname("Novotný");
        user.setRoleList(roleList);

        String tmp = null;

        User userServiceReturn;

        when(userDAO.findUserByLoginId(tmp)).thenReturn(null);
        when(roleService.getRoleListByName("USER")).thenReturn(roleList);
        when(userDAO.save(any())).thenAnswer(i -> i.getArguments()[0]);
        when(accountService.getAccountByNumber(tmp)).thenReturn(null);
        when(accountService.getAccountByNumber(tmp)).thenReturn(null);

        userServiceReturn = userService.addUser(user);

        //kontrola nezměnění údajů z formuláře
        Assert.assertEquals(userServiceReturn.getState(), user.getState());
        Assert.assertEquals(userServiceReturn.getZipCode(), user.getZipCode());
        Assert.assertEquals(userServiceReturn.getTown(), user.getTown());
        Assert.assertEquals(userServiceReturn.getSex(), user.getSex());
        Assert.assertEquals(userServiceReturn.getEmail(), user.getEmail());
        Assert.assertEquals(userServiceReturn.getLastname(), user.getLastname());
        Assert.assertEquals(userServiceReturn.getFirstname(), user.getFirstname());
        Assert.assertEquals(userServiceReturn.getAddressNumber(), user.getAddressNumber());
        Assert.assertEquals(userServiceReturn.getAddress(), user.getAddress());

        //ověření vygenerování login id
        Assert.assertNotNull(userServiceReturn.getLoginId());
        //ověření vygenerování pinu
        Assert.assertNotNull(userServiceReturn.getPin());
    }

    /**
     * Kontrola změny správných údajů při editaci uživatele
     */
    @Test
    public void editUser() {
        State state = new State();
        state.setName("Česká Republika");

        //role list
        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        User userFirst = new User();
        userFirst.setId(1);
        userFirst.setPin("12345");
        userFirst.setLoginId("12345678");
        userFirst.setRoleList(roleList);
        userFirst.setPid("1234567899");

        User user = new User();
        user.setSex("Muž");
        user.setTown("Písek");
        user.setState(state);
        user.setAddressNumber("44");
        user.setAddress("Zahradní");
        user.setEmail("test@test.cz");
        user.setZipCode("39701");
        user.setFirstname("Jan");
        user.setLastname("Novotný");
        user.setRoleList(roleList);

        when(userDAO.save(any())).thenAnswer(i -> i.getArguments()[0]);
        User second = userService.editUser(userFirst, user);

        //kontrola nezměnění údajů na null
        Assert.assertNotNull((second.getState()));
        Assert.assertNotNull(second.getZipCode());
        Assert.assertNotNull(second.getTown());
        Assert.assertNotNull(second.getSex());
        Assert.assertNotNull(second.getEmail());
        Assert.assertNotNull(second.getLastname());
        Assert.assertNotNull(second.getFirstname());
        Assert.assertNotNull(second.getAddressNumber());
        Assert.assertNotNull(second.getAddress());

        //kontrola přepsání starých nezměnitelných údajů
        Assert.assertEquals(second.getPid(), userFirst.getPid());
        Assert.assertEquals(second.getRoleList(), userFirst.getRoleList());
        Assert.assertEquals(second.getLoginId(), userFirst.getLoginId());
        Assert.assertEquals(second.getPin(), userFirst.getPin());
        Assert.assertEquals(second.getId(), userFirst.getId());

    }

}
