package dev.arack.enlace.profile.application.managers;

import dev.arack.enlace.iam.domain.aggregates.UserEntity;
import dev.arack.enlace.profile.application.dto.request.ProfileRequest;
import dev.arack.enlace.profile.application.dto.response.ProfileResponse;
import dev.arack.enlace.profile.application.port.input.services.ProfileService;
import dev.arack.enlace.profile.application.port.output.client.UserClient;
import dev.arack.enlace.profile.domain.entity.ProfileEntity;
import dev.arack.enlace.profile.domain.valueobject.Address;
import dev.arack.enlace.profile.domain.valueobject.FullName;
import dev.arack.enlace.profile.infrastructure.repository.ProfileJpaRepository;
import dev.arack.enlace.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProfileServiceManager implements ProfileService {

    private final ProfileJpaRepository profileJpaRepository;
    private final UserClient userClient;

    @Override
    public void createProfile(UserEntity user) {

        ProfileEntity profile = ProfileEntity.builder()
                .fullName(new FullName(user.getUsername(), ""))
                .email("example@mail.com")
                .address(new Address("", "", "", "", ""))
                .user(user)
                .build();

        profileJpaRepository.save(profile);
    }

    @Override
    public ProfileResponse getProfile() {

        Long currentUserId = userClient.getCurrentUser().id();
        ProfileEntity profileEntity = profileJpaRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        return ProfileResponse.fromEntity(profileEntity);
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest profileRequest) {

        Long currentUserId = userClient.getCurrentUser().id();
        ProfileEntity profileEntity = profileJpaRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        profileEntity.setFullName(new FullName(
                profileRequest.firstName(),
                profileRequest.lastName()
                ));
        profileEntity.setEmail(profileRequest.email());
        profileEntity.setAddress(new Address(
                profileRequest.street(),
                profileRequest.number(),
                profileRequest.city(),
                profileRequest.zipCode(),
                profileRequest.country()
                ));

        profileJpaRepository.save(profileEntity);
        return ProfileResponse.fromEntity(profileEntity);
    }

    @Override
    public void deleteProfile() {

        Long currentUserId = userClient.getCurrentUser().id();
        profileJpaRepository.deleteById(currentUserId);
    }

    @Override
    public ProfileResponse getProfileByUsername(String username) {

        ProfileEntity profileEntity = profileJpaRepository.findByUser_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        return ProfileResponse.fromEntity(profileEntity);
    }
}
