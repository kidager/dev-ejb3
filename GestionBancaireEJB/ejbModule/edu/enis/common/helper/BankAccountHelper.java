package edu.enis.common.helper;

import edu.enis.dto.BankAccountDTO;
import edu.enis.model.BankAccountEntity;

public class BankAccountHelper {

  public static BankAccountEntity dtoToModel(BankAccountDTO baDTO) {
    return new BankAccountEntity(baDTO.getClient(), baDTO.getBalance());
  }

}
