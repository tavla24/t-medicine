package com.milaev.medicine.dto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AccountDTOTest {

    private String str = "string";
    private boolean b = true;

    @Test
    public void methodsGetSet() {
        AccountDTO dto = new AccountDTO();
        RoleDTO roleDto = new RoleDTO();

        dto.setRole(roleDto);
        dto.setEdit(b);
        dto.setPassword(str);
        dto.setLogin(str);
        dto.setOldLogin(str);
        dto.setOldPassword(str);

        Assert.assertEquals(roleDto, dto.getRole());
        Assert.assertEquals(b, dto.isEdit());
        Assert.assertEquals(str, dto.getPassword());
        Assert.assertEquals(str, dto.getLogin());
        Assert.assertEquals(str, dto.getOldLogin());
        Assert.assertEquals(str, dto.getOldPassword());
    }
}
