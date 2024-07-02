package main;

import main.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import main.entity.Account;
import main.entity.Comment;
import main.entity.Rating;
import main.entity.Score;
import main.jigsawsudoku.UI.EndUI;
import main.jigsawsudoku.UI.EnterUI;
import main.jigsawsudoku.core.DataPush;
import main.jigsawsudoku.core.Game;


@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "main.server.*"))
public class SpringClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public Game game() {
        return new Game();
    }

    @Bean
    public CommandLineRunner runner(Game game) {
        return args -> game.play();
    }

    @Bean
    public DataPush dataPush() {
        return new DataPush();
    }

    @Bean
    public EndUI endUI() {
        return new EndUI();
    }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceRestClient();
        //return new ScoreServiceJPA();
    }

    @Bean
    public Score score() {
        return new Score();
    }

    @Bean
    public Rating rating() {
        return new Rating();
    }

    @Bean
    public Comment comment() {
        return new Comment();
    }


    @Bean
    public EnterUI enterUI() {
        return new EnterUI();
    }

    @Bean
    public Account account() {
        return new Account();
    }

    @Bean
    public AccountService accountService() {
        return new AccountServiceRestClient();
    }



    @Bean
    public RatingService ratingService() {
        return new RatingServiceRestClient();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceRestClient();
    }

    @Bean
    public ScoreServiceRestClient scoreServiceRestClient() {
        return new ScoreServiceRestClient();
    }

    @Bean
    public CommentServiceRestClient commentServiceRestClient() {
        return new CommentServiceRestClient();
    }

    @Bean
    public RatingServiceRestClient ratingServiceRestClient() {
        return new RatingServiceRestClient();
    }

    @Bean
    public AccountServiceRestClient accountServiceRestClient() {
        return new AccountServiceRestClient();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
