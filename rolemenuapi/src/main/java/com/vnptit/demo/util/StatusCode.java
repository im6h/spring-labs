package com.vnptit.demo.util;

/**
 * - ERR_001:  method not allow
 * - ERR_099: parameter in url invalid
 * - ERR_100: field required null, or field invalid format
 * - ERR_101: field unique.
 * - ERR_102: check length max
 * - ERR_103: format data invalid
 * - ERR_500: can't connect to to database
 * - NF_404: not found record with id
 * - Success_200: action OK
 * - Success_201: create action OK
 * - Success_204: action no content response
 */
public enum StatusCode {
  // code success
  SUCCESS_200,
  SUCCESS_201,
  SUCCESS_204,

  // code error
  ERR_001,
  ERR_099,
  ERR_100,
  ERR_101,
  ERR_102,
  ERR_103,
  ERR_500,

  // code not found
  NF_404
}
