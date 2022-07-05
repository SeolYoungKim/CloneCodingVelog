package ToyProject.CloneCodingVelog.web.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResult {
    private final String status;
    private final String message;
}
