package ma.exovate.medaibe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private Long adminId;
    private String firstName;
    private String lastName;
    private String email;
}
