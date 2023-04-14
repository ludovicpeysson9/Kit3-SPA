public class UserSetup {
    @Autowired
    UserRepository userRepo;


    @PostConstruct
    private void populateUserDatabase()
    {
        Iterable<UserEntity> userIt = userRepo.findAll();

        if ( ! userIt.iterator().hasNext())
        {
            String[][] users =  {{"client","jamaiscontent","ROLE_USER"}};
            for ( String[] userdat: users)
            {
                String pass = new BCryptPasswordEncoder().encode(userdat[1]);
                UserEntity userE = new UserEntity();
                userE.setUsername(userdat[0]);
                userE.setPassword(pass);
                userE.addGrantedAuthoity(userdat[2]);
                userE.setAccountNonLocked(true);
                userE.setEnabled(true);
                userE.setAccountNotExpired(true);
                userE.setCredentialsNonExpired(true);
                userRepo.save(userE);
            }
        }
    }

}
