package com.example.demo.dal.convert;

import com.example.demo.api.dto.BizUserCreateDTO;
import com.example.demo.api.dto.BizUserUpdateDTO;
import com.example.demo.api.vo.BizUserVO;
import com.example.demo.dal.entity.BizUser;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商户员工 Convert
 *
 * @author .
 */
@Mapper
public interface BizUserConvert {

  BizUserConvert INSTANCE = Mappers.getMapper(BizUserConvert.class);

  BizUserVO toVO(BizUser bean);

  List<BizUserVO> toVOList(List<BizUser> list);

  BizUser toDO(BizUserCreateDTO dto);

  BizUser toDO(BizUserUpdateDTO dto);

  BizUser clone(BizUser bean);

  BizUserVO clone(BizUserVO bean);
}
