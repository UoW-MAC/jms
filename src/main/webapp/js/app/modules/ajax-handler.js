/**
 * Created by Bright Huang on 11/5/14.
 */

define(['jquery', 'jms'], function($, jms) {

    "use strict";

    var ajaxHandler = {};

    ajaxHandler.sendRequest = function (options) {
        return $.ajax({
            cache: false,
            data: options.data,
            dataType: options.dataType,
            type: options.type,
            url: options.url
        }).success(function (data, status, xhr) {
            options.success(data);
        }).fail(function (xhr, status, error) {
            options.fail(error);
        }).always(function (xhr, status, error) {
        });
    };

    jms.AjaxHandler = ajaxHandler;
    return ajaxHandler;

});