import request from "@/utils/request";

export function getModuleList(data) {
    return request({
        url: '/module/v1/getModuleList',
        method: 'get',
        params: data
    })
}

export function getModuleDetail(data) {
    return request({
        url: '/module/v1/getModuleDetail',
        method: 'get',
        params: data
    })
}

export function saveOrUpdateModule(data) {
    return request({
        url: '/module/v1/saveOrUpdateModule',
        method: 'post',
        data: data
    })
}

export function removeModule(data) {
    return request({
        url: '/module/v1/removeModule',
        method: 'post',
        data: data
    })
}