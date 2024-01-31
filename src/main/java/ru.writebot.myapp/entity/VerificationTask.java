package ru.writebot.myapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "verification_task")
@NoArgsConstructor
public class VerificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ID пользователя который отправил задание на проверку задание
     * */
    @Column(name = "sender_id")
    private Long senderId;

    /**
     * ID пользователя который проверил задание
     * */
    @Column(name = "verifier_id")
    private Long verifierId;

    @Column(name = "task_id")
    private Long taskId;

    private String material;
    private boolean isVerified;

    public VerificationTask(Long senderId, Long verifierId, Long taskId, String material) {
        this.senderId = senderId;
        this.verifierId = verifierId;
        this.taskId = taskId;
        this.material = material;
        this.isVerified = false;
    }
}
