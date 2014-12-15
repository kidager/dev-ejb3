package edu.enis.common.helper;

import java.util.ArrayList;
import java.util.List;

import edu.enis.dto.ClientDTO;
import edu.enis.model.ClientEntity;

public class ClientHelper {

  public static ClientEntity dtoToModel(ClientDTO cDTO) {
    return new ClientEntity(cDTO.getCin(), cDTO.getFirstName(), cDTO.getLastName(), cDTO.getAddress());
  }

  public static ClientDTO modelToDTO(ClientEntity c) {
    return new ClientDTO(c.getCin(), c.getFirstName(), c.getLastName(), c.getAddress());
  }

  public static List<ClientDTO> modelListToDTOList(List<ClientEntity> listE) {
    if (listE == null)
      return null;
    List<ClientDTO> list = new ArrayList<ClientDTO>();
    for (ClientEntity cl : listE) {
      list.add(modelToDTO(cl));
    }
    return list;
  }

  public static List<ClientEntity> dtoListToModelList(List<ClientDTO> listDTO) {
    if (listDTO == null)
      return null;
    List<ClientEntity> list = new ArrayList<ClientEntity>();
    for (ClientDTO cl : listDTO) {
      list.add(dtoToModel(cl));
    }
    return list;
  }
}
