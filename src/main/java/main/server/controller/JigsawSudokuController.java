package main.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import main.jigsawsudoku.Content.Field;
import main.jigsawsudoku.Content.Tile;
import main.server.webservice.WinServiceRest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/JigsawSudoku")
public class JigsawSudokuController {

    List<String[][]> history = new ArrayList<>();

    private int score;

    private int difficult = 0;
    private Field field;

    @Autowired
    private WinServiceRest winServiceRest;
    private Tile[][] map;

    private String[][] colorMap;

    private boolean didStep = true;

    private String[][] writeMap(Tile[][] inputMap){
        String[][] currentMap = new String[inputMap.length][inputMap.length];

        for (int i = 0; i < inputMap.length; i++){
            for (int j = 0; j < inputMap[i].length; j++){
                if (inputMap[i][j].getState().equals("SHOWN")){
                    currentMap[i][j] = Character.toString(inputMap[i][j].getValue());
                }
                else{
                    currentMap[i][j] = " ";
                }
            }
        }
        return currentMap;
    }

    private void strMap(){
        System.out.println(history.size() - 2);
        for (int i = 0; i < history.get(history.size() - 2).length; i++){
            for (int j = 0; j < history.get(history.size() - 2)[i].length; j++){
                System.out.print(history.get(history.size() - 2)[i][j]);
//                if (history.get(history.size() - 2)[i][j].getState().equals("SHOWN")){
//                    System.out.print(history.get(history.size() - 2)[i][j].getValue());
//                }
//                else{
//                    System.out.print(" ");
//                }
            }
            System.out.println('\n');
        }
        System.out.println(history.size() - 1);
        for (int i = 0; i < history.get(history.size() - 1).length; i++){
            for (int j = 0; j < history.get(history.size() - 1)[i].length; j++){
                System.out.print(history.get(history.size() - 1)[i][j]);
//                if (history.get(history.size() - 1)[i][j].getState().equals("SHOWN")){
//                    System.out.print(history.get(history.size() - 1)[i][j].getValue());
//                }
//                else{
//                    System.out.print(" ");
//                }
            }
            System.out.println('\n');
        }
        System.out.println("-----------");
    }


    @RequestMapping("/play")
    public String jigsawSudoku(@RequestParam(required = false) String level, @RequestParam(required = false) Integer diff, @RequestParam(required = false) Integer row, @RequestParam(required = false) Integer column, @RequestParam(required = false) Integer value) {
        if (level != null && diff != null && !level.equals("0") && diff != 0){
            this.score = 0;
            winServiceRest.setScore(score);
            this.difficult = diff;
            field = new Field(new File(".\\src\\main\\java\\main\\jigsawsudoku\\levels\\level" + level), difficult);
            colorMap = field.getColorMap(new File(".\\src\\main\\java\\main\\jigsawsudoku\\levels\\level" + level + "color"));
            map = field.readLevel();
            history.add(writeMap(map));
        }
        else if (row != null && column != null && value != null){
            if (field.mapSet(row, column, value)){
                score += 10*difficult;
                winServiceRest.setScore(score);
                history.add(writeMap(map));
                this.didStep = true;
//                strMap();
            }

        }
        return "JigsawSudoku";
    }

    @RequestMapping("/play/back")
    public String backStep() {
        if (history.size() > 1 && didStep){
            field.backSetMap(history.get(history.size() - 2));
            score -= 10*difficult;
            winServiceRest.setScore(score);
            history.remove(history.size() - 1);
            didStep = false;
        }
        return "JigsawSudoku";
    }


    public String getMap(){
        StringBuilder htmlReturn = new StringBuilder();
        htmlReturn.append("<div class=\"map\">");
        for (int i = 0; i < map.length - 1; i++){
            for (int j = 0; j < map[i].length; j++){
                if (map[i][j].getState().equals("SHOWN")){
                    htmlReturn.append("<input style=\"background: " + colorMap[i][j] +"\" type=\"text\" inputmode=\"numeric\" readonly=\"readonly\" value=\"" + map[i][j].getValue() + "\">\n");
                }
                else {
                    htmlReturn.append("<input name=\""+ i + j +"\" class=\"input\" style=\"background: " + colorMap[i][j] +"\"  type=\"text\" maxlength = \"1\" inputmode=\"numeric\">\n");
                }
            }
            htmlReturn.append("<br>\n");
        }
        for (int j = 0; j < map[8].length; j++){
            if (map[8][j].getState().equals("SHOWN")){
                htmlReturn.append("<input style=\"background: " + colorMap[8][j] +"\" type=\"text\" inputmode=\"numeric\" readonly=\"readonly\" value=\"" + map[8][j].getValue() + "\">\n");
            }
            else {
                htmlReturn.append("<input name=\""+ 8 + j +"\" class=\"input\" style=\"background: " + colorMap[8][j] +"\"  type=\"text\" maxlength = \"1\" inputmode=\"numeric\">\n");
            }
        }
        htmlReturn.append("</div>");
        htmlReturn.append(
                "<div class=\"score-exit center\">\n" +
                "<h1>Your Score: " + score + "</h1>\n" +
                "<a class=\"floating-button\" href=\"#\" id=\"exitButton\">Exit</a>\n" +
                "<a class=\"floating-button\" href=\"http://localhost:8080/api/JigsawSudoku/play/back\">Back</a>\n" +
                "</div>\n");

        return htmlReturn.toString();
    }

    public boolean isSolved(){
        if (field.isSolved()){
            return true;
        }

        return false;
    }

}
