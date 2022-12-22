package com.fall2022_group20.dataAccessLayer.RoadMap;

import com.fall2022_group20.dataAccessLayer.Lesson.LessonSchema;
import com.fall2022_group20.dataAccessLayer.ScenarioGame.ScenarioGameSchema;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RoadMapSchema extends RealmObject {

    @PrimaryKey
    private Integer roadMapId;
    private String roadMapName;
    private RealmList<LessonSchema> lessons;
    private ScenarioGameSchema ScenarioGame;

    public Integer getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(Integer roadMapId) {
        this.roadMapId = roadMapId;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
    }

    public RealmList<LessonSchema> getLessons() {
        return lessons;
    }

    public void setLessons(RealmList<LessonSchema> lessons) {
        this.lessons = lessons;
    }

    public ScenarioGameSchema getScenarioGame() {
        return ScenarioGame;
    }

    public void setScenarioGame(ScenarioGameSchema scenarioGame) {
        ScenarioGame = scenarioGame;
    }
}
