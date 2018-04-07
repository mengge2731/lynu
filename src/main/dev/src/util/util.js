const code = {
  success: '0001', // 请求成功 0000
  noLogin: '0002', // 未登录  0001
  login: '0001', // 已登录 0002
  ILLEGAL_ARG_ERROR: '0003', // 请求参数错误
  UNKNOWN_ERROR: '0004', // 未知异常.
  SEND_MESSAGE_ERROR: '0005', // 发送短信异常.
  SEND_MESSAGE_SUCCESS: '0006', // 发送短信成功.
  DIFFERENT_PASSWORD_ERROR: '0007', // 两次密码不一致.
  MESSAGE_PHONE_ERROR: '2', // 手机格式不正确或者手机不能为空!
  MESSAGE_TOO_MUCH_ERROR: '22', // 1小时内最多只能发送3条验证码，每条有效时间为20分钟！您可能已经超限，无法继续发送！请等待超时后再试！
  MESSAGE_FREQUENTLY_ERROR: '33', // 超过频率，同一个手机号每30秒只能发送一条
  MESSAGE_WRONG: '0008', // 消息错误.
  MESSAGE_OVER_TIME: '0009', // 消息超时.
  APPLY_OWN_DATA: '0010', // 不能申请自己发布的数据.
  UPDATE_ERROR: '0011', // 更新失败.
  REGISTER_ERROR: '0012', // 注册失败.'),
  REGISTER_AGINE: '0013', // 已经注册过，请直接登录.
  LOGIN_ERROR: '0014', // 账号或密码错误.
  APPLY_AGINE_ERROR: '0015', // 已经申请过该数据.
}
const DingConf = function (data) {
  xxxxxxxxx
}
export {
  code
}
