package aiss.DailyMotionMiner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Channel already exists")
public class ChannelAlreadyExistsException extends Exception {
}