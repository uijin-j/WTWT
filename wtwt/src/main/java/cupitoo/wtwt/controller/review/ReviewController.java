package cupitoo.wtwt.controller.review;

import cupitoo.wtwt.annotation.Login;
import cupitoo.wtwt.controller.Error;
import cupitoo.wtwt.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 그룹에서 리뷰하기 -> 리뷰 당한 사람의 rate update!
     */
    @PostMapping("/{groupId}")
    public PostReviewRes postReviews(@Login Long sender,
                                  @PathVariable Long groupId,
                                  @RequestBody List<ReviewDto> request) throws IllegalAccessException {

        List<Long> result = new ArrayList<>();
        for (ReviewDto reviewDto : request) {
            result.add(reviewService.createReview(sender, groupId, reviewDto));
        }

        return new PostReviewRes(result);
    }

    /**
     * 리뷰 보기 -> 별점
     */


    /**
     * 예외 핸들러
     */
    @ExceptionHandler(IllegalAccessException.class)
    public Error handleIllegalAccessException(IllegalAccessException e) {
        return new Error(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
