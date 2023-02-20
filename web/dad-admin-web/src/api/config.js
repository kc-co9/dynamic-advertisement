import request from "@/utils/request";

export function getConfigExtraAttributeList(data) {
    return request({
        url: '/config/v1/getConfigExtraAttributeList',
        method: 'get',
        params: data
    })
}

export function getConfigExtraAttributeDetail(data) {
    return request({
        url: '/config/v1/getConfigExtraAttributeDetail',
        method: 'get',
        params: data
    })
}

export function saveOrUpdateConfigExtraAttribute(data) {
    return request({
        url: '/config/v1/saveOrUpdateConfigExtraAttribute',
        method: 'post',
        data: data
    })
}

export function removeConfigExtraAttribute(data) {
    return request({
        url: '/config/v1/removeConfigExtraAttribute',
        method: 'post',
        data: data
    })
}

export function getConfigList(data) {
    return request({
        url: '/config/v1/getConfigList',
        method: 'get',
        params: data
    })
}

export function getConfigDetail(data) {
    return request({
        url: '/config/v1/getConfigDetail',
        method: 'get',
        params: data
    })
}

export function saveOrUpdateConfig(data) {
    return request({
        url: '/config/v1/saveOrUpdateConfig',
        method: 'post',
        data: data
    })
}

export function removeConfig(data) {
    return request({
        url: '/config/v1/removeConfig',
        method: 'post',
        data: data
    })
}