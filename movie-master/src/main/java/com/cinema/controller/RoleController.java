package com.cinema.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cinema.bean.Role;
import com.cinema.controller.utils.Result;
import com.cinema.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 根据电影ID查询电影演员以及饰演角色的相关信息
     * @param movieId
     * @return
     */
    @GetMapping("/movieRoles")
    public Result selectMovieRole(String movieId){
        List<Role> roles = roleService.selectMovieRole(movieId);
        return new Result(roles!=null,roles);
    }

    /***
     * description:...
     * @param roleList
     * @return com.cinema.controller.utils.Result
     */
    @PostMapping("/saveRoles")
    public Result saveMovieRoles(@RequestBody List<Role> roleList) {
        boolean flag = false;
        String msg = null;
        try {
            flag = roleService.saveBatch(roleList);
        }catch (Exception e) {
            msg = "此演员在此电影中已经出演了一名角色";
        }
        return new Result(flag, msg);
    }

    @DeleteMapping("/deleteRole/{performerId}/{movieId}")
    public Result modifyRoles(@PathVariable String performerId, @PathVariable String movieId) {
        LambdaQueryWrapper<Role> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Role::getMovieId, movieId);
        lqw.eq(Role::getPerformerId, performerId);
        boolean remove = roleService.remove(lqw);
        return new Result(remove, remove ? "删除成功！":"删除失败");
    }


    @DeleteMapping("/deleteRoles")
    public Result deleteRoles(String movieId, String performerId) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("performer_id", performerId);
        queryWrapper.eq("movie_id", movieId);
        boolean remove = roleService.remove(queryWrapper);
        return new Result(remove);
    }

    @GetMapping("/getPerformerRoles")
    public Result getPerformerRoles(String performerId) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("r.*,m.movie_name,p.name");
        queryWrapper.last("r,t_movie m,t_performer p " +
                "WHERE p.performer_id = r.performer_id and r.movie_id = m.movie_id AND r.performer_id = " + "'" + performerId + "'");
        List<Role> roleList = roleService.list(queryWrapper);
        return new Result(!roleList.isEmpty(), roleList);
    }
}

