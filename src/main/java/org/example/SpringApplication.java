package org.example;

import org.example.entity.BaseEntity;
import org.example.entity.Printable;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApplication extends BaseEntity implements Printable {
    //    @Autowired
//    private TicketService ticketService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//      Iterable <Ticket> tickets = ticketService.get();

//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TicketService.class);
//        TicketDAO ticketDao = applicationContext.getBean(TicketDAO.class);
//        UserDAO userDao = applicationContext.getBean(UserDAO.class);
//
//        User user = new User("Rom", UserStatus.DEACTIVATED, new Timestamp(System.currentTimeMillis()));
//        User trio = userService.saveUser(user);
//        Optional<User> userOptional = userRepository.findById(user.getId());
//        System.out.println(userService.getUserById(user.getId()));
//
//        user.setName("Lisa");
//
//        userRepository.updateUserStatusById(UserStatus.ACTIVATED, user.getId());
//        user = userDao.get(user.getId());
//        System.out.println(user);
//
//
//        Ticket ticket = new Ticket(user, TicketType.DAY, new Timestamp(System.currentTimeMillis()));
//        Ticket tyu = ticketService.saveTicket(ticket);
//        user.setUserStatus(UserStatus.ACTIVATED);
//        userService.updateUserAndCreateTicket(user, ticket);

//        ticketDao.save(ticket);

//        System.out.println(ticketService.getTicket(ticket.getId()));
//        System.out.println(ticketService.getTicketByUserId(user.getId()));
//
//        ticketService.updateTicketType(ticket.getId(), TicketType.YEAR);

//        List<Ticket> ticketCollection = ticketService.getTicketByUserId(user.getId());
//        System.out.println(ticketCollection);
//        if (!ticketCollection.isEmpty()) {
//            for (Ticket ticketColl : ticketCollection) {
//                ticketService.delete(ticketColl.getId());
//            }
//        }
//        userService.deleteUser(user.getId());

//    }
}