<template>
  <div class="login-wrapper">
    <div class="bg-decor">
      <div class="bubble bubble-1"></div>
      <div class="bubble bubble-2"></div>
      <div class="bubble bubble-3"></div>
      <div class="bubble bubble-4"></div>
      <div class="grid-pattern"></div>
    </div>

    <div class="login-card">
      <div class="card-left">
        <div class="brand-wrap">
          <div class="brand-badge">
            <svg viewBox="0 0 24 24" width="38" height="38" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M3 13l2-2 4 4 8-8 4 4"/><path d="M3 18h18"/></svg>
          </div>
          <h1 class="brand-title">南京市雨花驾校</h1>
          <p class="brand-sub">管理信息系统 · Admin Console</p>
        </div>
        <ul class="feature-list">
          <li><span class="dot"></span>高效的学员与教练管理</li>
          <li><span class="dot"></span>实时训练与预约数据</li>
          <li><span class="dot"></span>智能化业务流程支持</li>
        </ul>
        <div class="left-footer">© {{ new Date().getFullYear() }} Driving School Admin</div>
      </div>

      <div class="card-right">
        <div class="right-inner">
          <div class="welcome">
            <h2>欢迎回来 👋</h2>
            <p>请登录您的管理员账户</p>
          </div>

          <el-form class="login-form" label-position="left" label-width="0px">
            <el-form-item class="field">
              <div class="field-wrap">
                <span class="field-icon">
                  <svg viewBox="0 0 24 24" width="18" height="18" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>
                </span>
                <el-input placeholder="请输入用户名" name="username" type="text" v-model="rulesForm.username" />
              </div>
            </el-form-item>

            <el-form-item class="field">
              <div class="field-wrap">
                <span class="field-icon">
                  <svg viewBox="0 0 24 24" width="18" height="18" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="11" width="18" height="11" rx="2" ry="2"/><path d="M7 11V7a5 5 0 0 1 10 0v4"/></svg>
                </span>
                <el-input placeholder="请输入密码" name="password" type="password" v-model="rulesForm.password" />
              </div>
            </el-form-item>

            <el-form-item v-if="roles.length>1" label="角色" prop="loginInRole" class="role">
              <el-radio
                v-for="item in roles"
                v-bind:key="item.roleName"
                v-model="rulesForm.role"
                :label="item.roleName"
              >{{item.roleName}}</el-radio>
            </el-form-item>

            <el-button type="primary" @click="login()" class="login-btn">
              <span>登 录</span>
              <svg viewBox="0 0 24 24" width="16" height="16" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="5" y1="12" x2="19" y2="12"/><polyline points="12 5 19 12 12 19"/></svg>
            </el-button>

            <div class="setting">
              <div class="register" @click="register('jiaolian')">注册教练 →</div>
              <div class="tourist" @click="touristVisit()">游客访问 →</div>
            </div>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

import { normalizeMenus } from "@/utils/menu";

export default {
  data() {
    return {
      rulesForm: {
        username: "",
        password: "",
        role: "",
        code: '',
      },
      menus: [],
      roles: [],
      tableName: "",
      codes: [{
        num: 1,
        color: '#000',
        rotate: '10deg',
        size: '16px'
      },{
        num: 2,
        color: '#000',
        rotate: '10deg',
        size: '16px'
      },{
        num: 3,
        color: '#000',
        rotate: '10deg',
        size: '16px'
      },{
        num: 4,
        color: '#000',
        rotate: '10deg',
        size: '16px'
      }],
    };
  },
  mounted() {
	this.getMenu();
  },
  created() {
    this.getRandCode()

  },
  methods: {
	getMenu() {
		let params = {
			page: 1,
			limit: 1,
			sort: 'id',
		}
		this.$http({
			url: "menu/list",
			method: "get",
			params: params
		}).then(({
			data
		}) => {
			if (data && data.code === 0) {
				this.menus = normalizeMenus(JSON.parse(data.data.list[0].menujson));
                for (let i = 0; i < this.menus.length; i++) {
                  if (this.menus[i].hasBackLogin=='是') {
                        this.roles.push(this.menus[i])
                  }
                }
				this.$storage.set("menus", this.menus);
			}
		})
	},
    register(tableName){
      this.$storage.set("loginTable", tableName);
      this.$router.push({path:'/register'})
    },
    // 游客访问
    touristVisit() {
      // 跳转到游客首页
      window.open('/springbooto2ehg/front/front/index.html', '_blank');
    },
    // 登陆
    login() {
      if (!this.rulesForm.username) {
         this.$message.error("请输入用户名");
        return;
      }
      if (!this.rulesForm.password) {
         this.$message.error("请输入密码");
        return;
      }
      if(this.roles.length>1) {
          if (!this.rulesForm.role) {
             this.$message.error("请选择角色");
            return;
          }
          let menus = this.menus;
          for (let i = 0; i < menus.length; i++) {
            if (menus[i].roleName == this.rulesForm.role) {
              this.tableName = menus[i].tableName;
            }
          }
      } else {
          this.tableName = this.roles[0].tableName;
          this.rulesForm.role = this.roles[0].roleName;
      }

      this.$http({
        url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
        method: "post"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$storage.set("Token", data.token);
          this.$storage.set("role", this.rulesForm.role);
          this.$storage.set("sessionTable", this.tableName);
          this.$storage.set("adminName", this.rulesForm.username);
          this.$router.replace({ path: "/index/" });
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    getRandCode(len = 4){
      this.randomString(len)
    },
    randomString(len = 4) {
      let chars = [
          "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
          "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
          "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
          "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
          "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2",
          "3", "4", "5", "6", "7", "8", "9"
      ]
      let colors = ["0", "1", "2","3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"]
      let sizes = ['14', '15', '16', '17', '18']

      let output = [];
      for (let i = 0; i < len; i++) {
        let key = Math.floor(Math.random()*chars.length)
        this.codes[i].num = chars[key]
        let code = '#'
        for (let j = 0; j < 6; j++) {
          let key = Math.floor(Math.random()*colors.length)
          code += colors[key]
        }
        this.codes[i].color = code
        let rotate = Math.floor(Math.random()*60)
        let plus = Math.floor(Math.random()*2)
        if(plus == 1) rotate = '-'+rotate
        this.codes[i].rotate = 'rotate('+rotate+'deg)'
        let size = Math.floor(Math.random()*sizes.length)
        this.codes[i].size = sizes[size]+'px'
      }
    },
  }
};
</script>
<style lang="scss" scoped>
.login-wrapper {
  min-height: 100vh;
  width: 100%;
  position: relative;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #f0f7ff 0%, #f7f0ff 50%, #fff5f7 100%);
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Microsoft YaHei", sans-serif;
}

.bg-decor {
  position: absolute;
  inset: 0;
  overflow: hidden;
  pointer-events: none;

  .bubble {
    position: absolute;
    border-radius: 50%;
    filter: blur(60px);
    opacity: 0.55;
    animation: float 14s ease-in-out infinite;
  }
  .bubble-1 {
    width: 420px; height: 420px;
    background: radial-gradient(circle, #a8d8ff 0%, transparent 70%);
    top: -120px; left: -80px;
  }
  .bubble-2 {
    width: 380px; height: 380px;
    background: radial-gradient(circle, #ffc2d8 0%, transparent 70%);
    bottom: -120px; right: -80px;
    animation-delay: -5s;
  }
  .bubble-3 {
    width: 300px; height: 300px;
    background: radial-gradient(circle, #c9b6ff 0%, transparent 70%);
    top: 40%; right: 20%;
    animation-delay: -9s;
  }
  .bubble-4 {
    width: 260px; height: 260px;
    background: radial-gradient(circle, #b5f0e0 0%, transparent 70%);
    bottom: 15%; left: 25%;
    animation-delay: -3s;
  }

  .grid-pattern {
    position: absolute;
    inset: 0;
    background-image:
      linear-gradient(rgba(120, 140, 180, 0.06) 1px, transparent 1px),
      linear-gradient(90deg, rgba(120, 140, 180, 0.06) 1px, transparent 1px);
    background-size: 40px 40px;
    mask-image: radial-gradient(ellipse at center, black 40%, transparent 75%);
    -webkit-mask-image: radial-gradient(ellipse at center, black 40%, transparent 75%);
  }
}

@keyframes float {
  0%, 100% { transform: translate(0, 0) scale(1); }
  33% { transform: translate(30px, -30px) scale(1.05); }
  66% { transform: translate(-20px, 20px) scale(0.95); }
}

.login-card {
  position: relative;
  z-index: 2;
  display: flex;
  width: #{"min(980px, 92vw)"};
  min-height: 560px;
  background: rgba(255, 255, 255, 0.75);
  backdrop-filter: blur(24px) saturate(160%);
  -webkit-backdrop-filter: blur(24px) saturate(160%);
  border: 1px solid rgba(255, 255, 255, 0.9);
  border-radius: 24px;
  box-shadow:
    0 20px 60px rgba(100, 120, 200, 0.18),
    0 4px 12px rgba(100, 120, 200, 0.08),
    inset 0 1px 0 rgba(255, 255, 255, 0.9);
  overflow: hidden;
  animation: cardIn 0.7s cubic-bezier(0.22, 1, 0.36, 1);
}

@keyframes cardIn {
  from { opacity: 0; transform: translateY(20px) scale(0.98); }
  to { opacity: 1; transform: translateY(0) scale(1); }
}

.card-left {
  flex: 1;
  padding: 48px 44px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background: linear-gradient(135deg, rgba(99, 162, 255, 0.12) 0%, rgba(194, 133, 255, 0.12) 60%, rgba(255, 143, 178, 0.12) 100%);
  position: relative;

  &::before {
    content: "";
    position: absolute;
    inset: 0;
    background: radial-gradient(circle at 20% 20%, rgba(255,255,255,0.5), transparent 50%);
    pointer-events: none;
  }

  .brand-wrap {
    position: relative;

    .brand-badge {
      width: 62px;
      height: 62px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 18px;
      background: linear-gradient(135deg, #6ea8ff 0%, #a66fff 100%);
      color: #fff;
      box-shadow: 0 10px 26px rgba(110, 130, 255, 0.38);
      margin-bottom: 22px;
    }

    .brand-title {
      font-size: 26px;
      font-weight: 700;
      color: #1f2a44;
      margin: 0 0 8px 0;
      letter-spacing: 0.5px;
    }

    .brand-sub {
      font-size: 13px;
      color: #6b7896;
      margin: 0;
      letter-spacing: 1px;
    }
  }

  .feature-list {
    list-style: none;
    padding: 0;
    margin: 24px 0;
    position: relative;

    li {
      display: flex;
      align-items: center;
      font-size: 14px;
      color: #4a5677;
      padding: 10px 0;

      .dot {
        width: 8px;
        height: 8px;
        border-radius: 50%;
        background: linear-gradient(135deg, #6ea8ff, #a66fff);
        margin-right: 12px;
        box-shadow: 0 0 0 4px rgba(110, 130, 255, 0.12);
      }
    }
  }

  .left-footer {
    font-size: 12px;
    color: #8a96b5;
    position: relative;
  }
}

.card-right {
  flex: 1.05;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 48px 52px;
  background: rgba(255, 255, 255, 0.55);

  .right-inner {
    width: 100%;
    max-width: 360px;
  }

  .welcome {
    margin-bottom: 32px;

    h2 {
      font-size: 28px;
      font-weight: 700;
      color: #1f2a44;
      margin: 0 0 8px 0;
    }
    p {
      font-size: 14px;
      color: #7d8aa8;
      margin: 0;
    }
  }
}

.login-form {
  .field {
    margin-bottom: 18px !important;

    &::v-deep .el-form-item__content {
      line-height: initial;
    }

    .field-wrap {
      position: relative;
      display: flex;
      align-items: center;
      background: #fff;
      border: 1.5px solid #e4e8f3;
      border-radius: 12px;
      transition: all 0.25s ease;
      overflow: hidden;

      &:hover {
        border-color: #c2d1f5;
      }

      &:focus-within {
        border-color: #6ea8ff;
        box-shadow: 0 0 0 4px rgba(110, 168, 255, 0.15);
      }

      .field-icon {
        padding: 0 10px 0 16px;
        color: #8a96b5;
        display: flex;
        align-items: center;
      }

      &::v-deep .el-input {
        flex: 1;

        input {
          border: 0;
          background: transparent;
          height: 46px;
          line-height: 46px;
          font-size: 14px;
          color: #1f2a44;
          padding: 0 16px 0 4px;
          width: 100%;

          &::placeholder {
            color: #a8b2cc;
          }
        }
      }
    }
  }

  .role {
    margin-bottom: 18px !important;

    &::v-deep .el-form-item__label {
      color: #4a5677;
      font-size: 14px;
      padding-right: 10px;
    }

    &::v-deep .el-radio__label {
      color: #4a5677;
    }

    &::v-deep .el-radio.is-checked .el-radio__label {
      color: #6ea8ff;
    }

    &::v-deep .el-radio__inner {
      border-color: #c2d1f5;
    }

    &::v-deep .el-radio.is-checked .el-radio__inner {
      background-color: #6ea8ff;
      border-color: #6ea8ff;
    }
  }

  .login-btn {
    width: 100%;
    height: 48px;
    margin-top: 8px;
    padding: 0;
    border: 0;
    border-radius: 12px;
    background: linear-gradient(135deg, #6ea8ff 0%, #a66fff 100%);
    color: #fff;
    font-size: 15px;
    font-weight: 600;
    letter-spacing: 4px;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    cursor: pointer;
    box-shadow: 0 8px 24px rgba(110, 130, 255, 0.35);
    transition: transform 0.2s ease, box-shadow 0.2s ease, filter 0.2s ease;

    &:hover {
      transform: translateY(-1px);
      box-shadow: 0 12px 32px rgba(110, 130, 255, 0.45);
      filter: brightness(1.05);
    }

    &:active {
      transform: translateY(0);
    }

    span { letter-spacing: 4px; }
  }

  .setting {
    margin-top: 20px;
    text-align: center;
    display: flex;
    justify-content: center;
    gap: 30px;

    .register {
      display: inline-block;
      font-size: 13px;
      color: #6ea8ff;
      cursor: pointer;
      padding: 6px 10px;
      border-radius: 8px;
      transition: all 0.2s ease;

      &:hover {
        background: rgba(110, 168, 255, 0.08);
        color: #4f8bff;
      }
    }

    .tourist {
      display: inline-block;
      font-size: 13px;
      color: #6ea8ff;
      cursor: pointer;
      padding: 6px 10px;
      border-radius: 8px;
      transition: all 0.2s ease;

      &:hover {
        background: rgba(110, 168, 255, 0.08);
        color: #4f8bff;
      }
    }
  }
}

@media (max-width: 768px) {
  .login-card {
    flex-direction: column;
    width: 92vw;
    min-height: auto;
  }
  .card-left {
    padding: 32px 28px;
  }
  .card-right {
    padding: 32px 28px 40px;
  }
}
</style>
