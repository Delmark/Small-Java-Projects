package by.delmark.websocketapp.repository;

import by.delmark.websocketapp.models.Chat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class ChatRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Chat chat) {
        jdbcTemplate.update("INSERT INTO chats (chat_name, chat_desc) VALUES (?,?)", chat.getChatName(), chat.getChatDesc());
    }

    public void update(Chat chat) {
        jdbcTemplate.update("UPDATE chats SET chat_name =?, chat_desc =? WHERE chat_id =?", chat.getChatName(), chat.getChatDesc(), chat.getChatId());
    }

    public boolean existsByChatName(String chatName) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM chats WHERE chat_name =?", Integer.class, chatName) > 0;
    }

    public Optional<Chat> getChatById(Long chatId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM chats WHERE chat_id = ?", new BeanPropertyRowMapper<>(Chat.class), chatId));
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Chat> getAll() {
        return jdbcTemplate.query("SELECT * from chats", new BeanPropertyRowMapper<>(Chat.class));
    }

    public Integer getChatCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM chats", Integer.class);
    }

    public void deleteById(Long chatId) {
        jdbcTemplate.update("DELETE FROM chats WHERE chat_id = ?", chatId);
    }
}
