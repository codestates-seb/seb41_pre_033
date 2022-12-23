package server.user.mapper;

import org.mapstruct.Mapper;
import server.user.dto.UserDto;
import server.user.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userPostToUser(UserDto.Post requestBody);
    User userPatchToUser(UserDto.Patch requestBody);
    UserDto.Response userToUserResponse(User user);
    List<UserDto.Response> usersToUserResponse(List<User> users);
}
