package dev.arack.enlace.profile.application.managers;

import dev.arack.enlace.iam.application.facades.AuthenticationFacade;
import dev.arack.enlace.iam.application.port.output.persistence.UserPersistence;
import dev.arack.enlace.iam.domain.aggregates.UserEntity;
import dev.arack.enlace.iam.infrastructure.repository.UserJpaRepository;
import dev.arack.enlace.profile.application.port.input.services.ConnectionService;
import dev.arack.enlace.shared.exceptions.ResourceNotFoundException;
import dev.arack.enlace.timeline.application.dto.response.FollowResponse;
import dev.arack.enlace.profile.application.port.output.persistence.ConnectionPersistence;
import dev.arack.enlace.profile.domain.entity.ConnectionEntity;
import dev.arack.enlace.profile.infrastructure.repository.ConnectionJpaRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionServiceManager implements ConnectionService {

    private final UserPersistence userPersistence;
    private final ConnectionPersistence connectionPersistence;
    private final AuthenticationFacade authenticationFacade;

    private Long getCurrentUserId() {
        return authenticationFacade.getCurrentUser().getId();
    }

    @Override
    public void followUser(Long followedId) {

        if (getCurrentUserId().equals(followedId)) {
            throw new ValidationException("Cannot follow yourself");
        }
        UserEntity follower = userPersistence.findById(getCurrentUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Follower not found"));

        UserEntity followed = userPersistence.findById(followedId)
                .orElseThrow(() -> new ResourceNotFoundException("Followed user not found"));

        if (connectionPersistence.existsByFollowerAndFollowed(follower, followed)) {
            throw new ValidationException("Already following this user");
        }
        ConnectionEntity connectionEntity = new ConnectionEntity();
        connectionEntity.setFollower(follower);
        connectionEntity.setFollowed(followed);

        connectionPersistence.followUser(connectionEntity);
    }

    @Override
    public void unfollowUser(Long followedId) {

        if (getCurrentUserId().equals(followedId)) {
            throw new IllegalArgumentException("Cannot unfollow yourself");
        }
        ConnectionEntity connectionEntity = connectionPersistence.findByFollowerIdAndFollowedId(getCurrentUserId(), followedId)
                .orElseThrow(() -> new IllegalArgumentException("Follow relationship does not exist"));

        connectionPersistence.unfollowUser(connectionEntity);
    }

    @Override
    public List<FollowResponse> getFollowing() {

        List<ConnectionEntity> connectionEntityList = connectionPersistence.getFollowing(getCurrentUserId());
        return FollowResponse.fromFollowEntityList(connectionEntityList);
    }

    @Override
    public List<FollowResponse> getFollowers() {

        List<ConnectionEntity> connectionEntityList = connectionPersistence.getFollowers(getCurrentUserId());
        return FollowResponse.fromFollowEntityList(connectionEntityList);
    }
}
