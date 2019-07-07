package by.babanin.testtask.config;

import by.babanin.testtask.dao.RoomRepository;
import by.babanin.testtask.dao.UserRepository;
import by.babanin.testtask.dao.WorkScheduleRepository;
import by.babanin.testtask.entity.Period;
import by.babanin.testtask.entity.Room;
import by.babanin.testtask.entity.User;
import by.babanin.testtask.entity.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

@Configuration
public class LoadDatabase {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final WorkScheduleRepository workScheduleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${initdb.admin.username}")
    private String adminUsername;
    @Value("${initdb.admin.password}")
    private String adminPassword;
    @Value("${initdb.user.username}")
    private String userUsername;
    @Value("${initdb.user.password}")
    private String userPassword;
    @Value("${initdb.room1.number}")
    private String room1Number;
    @Value("${initdb.room1.price}")
    private Double room1Price;
    @Value("${initdb.room2.number}")
    private String room2Number;
    @Value("${initdb.room2.price}")
    private Double room2Price;

    public LoadDatabase(
            UserRepository userRepository,
            RoomRepository roomRepository,
            WorkScheduleRepository workScheduleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.workScheduleRepository = workScheduleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            userRepository.deleteAll();
            roomRepository.deleteAll();
            workScheduleRepository.deleteAll();

            createUser(adminUsername, adminPassword);
            createUser(userUsername, userPassword);

            createRoom(room1Number, room1Price);
            createRoom(room2Number, room2Price);

            User admin = userRepository.findByUsername(adminUsername);
            Room room = roomRepository.findByNumber(room2Number);
            Period period = new Period();
            period.setStart(LocalDateTime.now().minusDays(2));
            period.setFinish(LocalDateTime.now().plusDays(2));
            Map<String, Period> map = new HashMap<>();
            map.put(admin.getId(), period);
            room.setReservation(map);
            roomRepository.save(room);

            System.out.println("----- Profiles ------");
            List<User> users = userRepository.findAll();
            users.forEach(System.out::println);
            System.out.println("----- Rooms ------");
            List<Room> rooms = roomRepository.findAll();
            rooms.forEach(System.out::println);
        };
    }

    private void createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.USER);
        if (!username.equals(userUsername))
            roles.add(UserRole.ADMINISTRATOR);
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private void createRoom(String number, Double price) {
        Room room = new Room();
        room.setNumber(number);
        room.setPrice(price);

        roomRepository.save(room);
    }
}
