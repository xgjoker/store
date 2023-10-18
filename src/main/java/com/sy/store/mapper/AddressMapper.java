package com.sy.store.mapper;

import com.sy.store.entity.Address;

import java.util.List;

public interface AddressMapper {

    Integer insert(Address address);

    Integer countByUid(Integer uid);

    List<Address> findByUid(Integer uid);

    Address findLastModified(Integer uid);

    // VO = Value Object - object only directly map with table (entity usually map with table)
    //List<Address> findVOByCid(List<Integer> cid);
    // <select id="findVOByCid" resultType="com.zc.store.vo.ShopCarVO">
    //        select cid,
    //        uid,
    //        pid,
    //        t_cart.price,
    //        t_cart.num,
    //        t_product.title,
    //        t_product.price AS realPrice,
    //        t_product.image
    //        from t_cart
    //        left join t_product on t_cart.pid = t_product.id
    //        where cid in(
    //        <foreach collection="array" item="cid" separator=",">
    //            #{cid}
    //        </foreach>
    //        )
    //        order by t_cart.created_time desc
    //    </select>
}
