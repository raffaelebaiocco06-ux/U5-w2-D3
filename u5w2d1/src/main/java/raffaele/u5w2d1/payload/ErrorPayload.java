package raffaele.u5w2d1.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorPayload {
    private String message;
    private LocalDateTime timestamp;
}

