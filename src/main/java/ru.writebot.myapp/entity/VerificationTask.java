package ru.writebot.myapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationTask that = (VerificationTask) o;
        return isVerified == that.isVerified && Objects.equals(id, that.id) && Objects.equals(senderId, that.senderId) && Objects.equals(verifierId, that.verifierId) && Objects.equals(taskId, that.taskId) && Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderId, verifierId, taskId, material, isVerified);
    }
}
