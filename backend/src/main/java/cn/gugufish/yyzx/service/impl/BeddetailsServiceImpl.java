package cn.gugufish.yyzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gugufish.yyzx.pojo.dto.BedDetailsDTO;
import cn.gugufish.yyzx.pojo.dto.ExchangeDTO;
import cn.gugufish.yyzx.mapper.BedMapper;
import cn.gugufish.yyzx.mapper.BeddetailsMapper;
import cn.gugufish.yyzx.mapper.CustomerMapper;
import cn.gugufish.yyzx.pojo.Bed;
import cn.gugufish.yyzx.pojo.Beddetails;
import cn.gugufish.yyzx.pojo.Customer;
import cn.gugufish.yyzx.service.BeddetailsService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.pojo.vo.BedDetailsVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BeddetailsServiceImpl extends ServiceImpl<BeddetailsMapper, Beddetails> implements BeddetailsService {

    @Resource
    private BeddetailsMapper beddetailsMapper;
    @Resource
    private BedMapper bedMapper;
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public ResultVo<Page<BedDetailsVo>> listBedDetailsVoPage(BedDetailsDTO bedDetailsDTO) throws Exception {
        Page<BedDetailsVo> page = new Page<>(bedDetailsDTO.getPageSize(), 10);
        beddetailsMapper.selectBedDetailsVo(page, bedDetailsDTO);
        return ResultVo.ok(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVo<Void> exchangeBed(ExchangeDTO exchangeDTO) throws Exception {
        //查询床位是否可用
        Bed bed = bedMapper.selectById(exchangeDTO.getNewBedId());
        if (bed.getBedStatus() != 1) {
            return ResultVo.fail("该床位已有人");
        }
        //①修改客户旧床位详情信息：is_deleted为1 床位使用结束时间为当前日期
        Beddetails beddetails = new Beddetails();
        beddetails.setId(exchangeDTO.getId());
        beddetails.setIsDeleted(1);
        beddetails.setEndDate(new Date());//结束时间为当前日期
        int row1 = beddetailsMapper.updateById(beddetails);
        //②添加新床位记录的记录
        Beddetails newBeddetails = new Beddetails();
        newBeddetails.setIsDeleted(0);
        newBeddetails.setCustomerId(exchangeDTO.getCustomerId());
        newBeddetails.setBedId(exchangeDTO.getNewBedId());
        newBeddetails.setEndDate(exchangeDTO.getEndDate());
        newBeddetails.setStartDate(new Date());//开始时间为当前日期
        newBeddetails.setBedDetails(exchangeDTO.getBuildingNo() + "#" + bed.getBedNo());
        int row2 = beddetailsMapper.insert(newBeddetails);
        //③修改旧床位的状态为空闲 bed_status=1
        Bed oldBed = new Bed();
        oldBed.setId(exchangeDTO.getOldBedId());
        oldBed.setBedStatus(1);
        int row3 = bedMapper.updateById(oldBed);
        //④修改新床位的状态为有人 bed_status=2
        Bed newdBed = new Bed();
        newdBed.setId(exchangeDTO.getNewBedId());
        newdBed.setBedStatus(2);
        int row4 = bedMapper.updateById(newdBed);
        //⑤修改客户信息- 新房间号  新床位号 楼号
        Customer customer = new Customer();
        customer.setId(exchangeDTO.getCustomerId());
        customer.setBedId(exchangeDTO.getNewBedId());
        customer.setBuildingNo(exchangeDTO.getBuildingNo());
        customer.setRoomNo(exchangeDTO.getNewRoomNo());
        int row5 = customerMapper.updateById(customer);
        if (!(row1 > 0 && row2 > 0 && row3 > 0 && row4 > 0 && row5 > 0)) {
            throw new Exception("床位调换失败");
        }
        return ResultVo.ok("床位调换成功");
    }

    @Override
    public ResultVo<BedDetailsVo> getBedPersonInfoByBedId(Integer bedId) throws Exception {
        // 查询当前床位的有效床位详情信息（is_deleted=0且结束日期大于当前日期）
        QueryWrapper<Beddetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bed_id", bedId)
                .eq("is_deleted", 0)
                   .orderByDesc("start_date")
                   .last("LIMIT 1");
        
        Beddetails beddetails = beddetailsMapper.selectOne(queryWrapper);

        if (beddetails == null) {
            return ResultVo.fail("该床位暂无人员信息");
        }
        // 查询客户信息
        Customer customer = customerMapper.selectById(beddetails.getCustomerId());
        if (customer == null) {
            return ResultVo.fail("未找到客户信息");
        }
        
        // 查询床位信息
        Bed bed = bedMapper.selectById(bedId);
        if (bed == null) {
            return ResultVo.fail("未找到床位信息");
        }
        if(bed.getBedStatus() == 1)
        {
            return ResultVo.fail("当前床位无人员信息");
        }
        // 构建返回的BedDetailsVo对象
        BedDetailsVo bedDetailsVo = new BedDetailsVo();
        bedDetailsVo.setId(beddetails.getId());
        bedDetailsVo.setIsDeleted(beddetails.getIsDeleted());
        bedDetailsVo.setStartDate(beddetails.getStartDate());
        bedDetailsVo.setEndDate(beddetails.getEndDate());
        bedDetailsVo.setBedDetails(beddetails.getBedDetails());
        bedDetailsVo.setCustomerId(beddetails.getCustomerId());
        bedDetailsVo.setBedId(beddetails.getBedId());
        bedDetailsVo.setCustomerName(customer.getCustomerName());
        bedDetailsVo.setCustomerSex(customer.getCustomerSex());
        bedDetailsVo.setRoomNo(String.valueOf(bed.getRoomNo()));
        
        return ResultVo.ok(bedDetailsVo);
    }
}