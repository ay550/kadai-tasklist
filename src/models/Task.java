//必要なデータを格納するクラスDTO
package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity

//一覧表示するデータを取得するためのJPQLを追記
//@NamedQueries アノテーションと @NamedQuery アノテーションを利用

@NamedQueries({
    @NamedQuery(
        name = "getAllTasks",
        query = "SELECT m FROM Task AS m ORDER BY m.id DESC"
    )       //SELECT m は SELECT * と同じ意味
})

@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")  //id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主キーを自動採番
    private Integer id;


    @Column(name = "created_at", nullable = false)  //作成日時
    private Timestamp created_at;


    @Column(name = "updated_at", nullable = false)  //更新日時
    private Timestamp updated_at;


    @Column(name = "content", length = 255, nullable = false)
    private String content;     //タスクの内容


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Timestamp getCreated_at() {
        return created_at;
    }


    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }


    public Timestamp getUpdated_at() {
        return updated_at;
    }


    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }





}
