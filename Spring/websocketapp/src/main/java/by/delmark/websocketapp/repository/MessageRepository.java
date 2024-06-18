package by.delmark.websocketapp.repository;

import by.delmark.websocketapp.models.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@AllArgsConstructor
public class MessageRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Message message) {
        jdbcTemplate.update("INSERT INTO chat_messages (chat_id, message_text) VALUES (?,?)", message.getChatId(), message.getMessageText());
    }

    public Optional<Message> getById(Long messageId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * from chat_messages WHERE message_id =?", new BeanPropertyRowMapper<>(Message.class), messageId));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void update(Message message) {
        jdbcTemplate.update("UPDATE chat_messages SET message_text =? WHERE message_id =?", message.getMessageText(), message.getMessageId());
    }

    public List<Message> getAll() {
        return jdbcTemplate.query("SELECT * from chat_messages", new BeanPropertyRowMapper<Message>());
    }

    public List<Message> getAllByChatId(Long chatId) {
        return jdbcTemplate.query("SELECT * from chat_messages WHERE chat_id =?", new BeanPropertyRowMapper<>(Message.class), chatId);
    }

    public Integer getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM chat_messages", Integer.class);
    }

    public void deleteById(Long messageId) {
        jdbcTemplate.update("DELETE FROM chat_messages WHERE message_id = ?", messageId);
    }
}
