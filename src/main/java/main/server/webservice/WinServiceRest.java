package main.server.webservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/winCheck")
public class WinServiceRest {

    private int score = 0;


    public void setScore(int score) {
        this.score = score;
    }

    @GetMapping("/{game}/score")
    public Integer score(@PathVariable String game) {
        System.out.println("Returned score: " + score);
        return score;
    }

}
