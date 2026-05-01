<template>
  <div class="dash-wrap">
    <div class="hero">
      <div class="hero-left">
        <div class="hero-tag">Dashboard · 控制台</div>
        <h1 class="hero-title">欢迎回来，<span class="grad-text">{{ adminName || '管理员' }}</span></h1>
        <p class="hero-desc">这里是 {{ $project.projectName }}，祝您今天工作愉快 ✨</p>
        <div class="hero-meta">
          <div class="meta-item">
            <span class="meta-label">当前角色</span>
            <span class="meta-value">{{ role || '—' }}</span>
          </div>
          <div class="meta-item">
            <span class="meta-label">今日日期</span>
            <span class="meta-value">{{ today }}</span>
          </div>
          <div class="meta-item">
            <span class="meta-label">当前时刻</span>
            <span class="meta-value">{{ clock }}</span>
          </div>
        </div>
      </div>
      <div class="hero-right">
        <div class="orb orb-a"></div>
        <div class="orb orb-b"></div>
        <div class="orb orb-c"></div>
        <svg class="hero-illu" viewBox="0 0 200 200" fill="none">
          <defs>
            <linearGradient id="g1" x1="0" y1="0" x2="1" y2="1">
              <stop offset="0%" stop-color="#6ea8ff"/>
              <stop offset="100%" stop-color="#a66fff"/>
            </linearGradient>
          </defs>
          <circle cx="100" cy="100" r="70" stroke="url(#g1)" stroke-width="2" stroke-dasharray="4 6" opacity="0.6"/>
          <circle cx="100" cy="100" r="46" stroke="url(#g1)" stroke-width="2" opacity="0.35"/>
          <circle cx="100" cy="100" r="22" fill="url(#g1)" opacity="0.9"/>
        </svg>
      </div>
    </div>

    <div class="stat-grid">
      <div class="stat-card" v-for="(s,i) in stats" :key="i" :style="{'--c1':s.c1,'--c2':s.c2}">
        <div class="stat-icon">
          <span v-html="s.icon"></span>
        </div>
        <div class="stat-body">
          <div class="stat-label">{{ s.label }}</div>
          <div class="stat-value">{{ s.value }}</div>
          <div class="stat-sub">
            <span :class="['trend', s.up ? 'up' : 'down']">{{ s.up ? '▲' : '▼' }} {{ s.delta }}</span>
            <span class="stat-hint">较昨日</span>
          </div>
        </div>
      </div>
    </div>

    <div class="panel-row">
      <div class="panel chart-panel">
        <div class="panel-head">
          <div class="panel-title">近7日活跃趋势</div>
          <div class="panel-chip">实时</div>
        </div>
        <div class="bar-chart">
          <div class="bar-col" v-for="(b,i) in bars" :key="i">
            <div class="bar" :style="{height: b + '%'}"></div>
            <div class="bar-label">{{ weekLabels[i] }}</div>
          </div>
        </div>
      </div>

      <div class="panel quick-panel">
        <div class="panel-head">
          <div class="panel-title">快捷入口</div>
        </div>
        <div class="quick-grid">
          <div class="quick-item" v-for="(q,i) in quickLinks" :key="i">
            <div class="quick-icon" v-html="q.icon"></div>
            <div class="quick-text">{{ q.text }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import router from '@/router/router-static'
export default {
	data() {
		return {
			adminName: '',
			role: '',
			today: '',
			clock: '',
			timer: null,
			weekLabels: ['周一','周二','周三','周四','周五','周六','周日'],
			bars: [],
			stats: [
				{label:'在校学员', value:'0', delta:'0%', up:true, c1:'#6ea8ff', c2:'#a66fff',
				 icon:'<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>'},
				{label:'教练数量', value:'0', delta:'0%', up:true, c1:'#ff95c7', c2:'#ff7b9a',
				 icon:'<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M17 21v-2a4 4 0 0 0-3-3.87"/><path d="M13 3.13a4 4 0 0 1 0 7.75"/><path d="M3 21v-2a4 4 0 0 1 4-4h4a4 4 0 0 1 4 4v2"/><circle cx="9" cy="7" r="4"/></svg>'},
				{label:'今日预约', value:'0', delta:'0%', up:true, c1:'#5ed8c3', c2:'#6ea8ff',
				 icon:'<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="4" width="18" height="18" rx="2"/><line x1="16" y1="2" x2="16" y2="6"/><line x1="8" y1="2" x2="8" y2="6"/><line x1="3" y1="10" x2="21" y2="10"/></svg>'},
				{label:'本月考试', value:'0', delta:'0%', up:false, c1:'#ffb980', c2:'#ff8fb0',
				 icon:'<svg viewBox="0 0 24 24" width="22" height="22" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/><polyline points="14 2 14 8 20 8"/><line x1="9" y1="15" x2="15" y2="15"/></svg>'},
			],
			quickLinks: [
				{text:'学员管理', icon:'<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>'},
				{text:'教练管理', icon:'<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/></svg>'},
				{text:'预约中心', icon:'<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="4" width="18" height="18" rx="2"/><line x1="16" y1="2" x2="16" y2="6"/><line x1="8" y1="2" x2="8" y2="6"/><line x1="3" y1="10" x2="21" y2="10"/></svg>'},
				{text:'课程安排', icon:'<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"/><path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"/></svg>'},
				{text:'数据报表', icon:'<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="12" y1="20" x2="12" y2="10"/><line x1="18" y1="20" x2="18" y2="4"/><line x1="6" y1="20" x2="6" y2="16"/></svg>'},
				{text:'系统设置', icon:'<svg viewBox="0 0 24 24" width="20" height="20" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="3"/><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-4 0v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1 0-4h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 2.83-2.83l.06.06A1.65 1.65 0 0 0 9 4.6a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 4 0v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9c.36.16.75.25 1.15.26H21a2 2 0 0 1 0 4h-.09a1.65 1.65 0 0 0-1.51 1z"/></svg>'},
			],
		};
	},
  mounted(){
    this.init();
    this.adminName = this.$storage.get('adminName') || ''
    this.role = this.$storage.get('role') || ''
    this.setToday()
    this.tickClock()
    this.timer = setInterval(this.tickClock, 1000)
    this.getStats()
    this.getActivity()
  },
  beforeDestroy(){
    if(this.timer) clearInterval(this.timer)
  },
  methods:{
    init(){
        if(this.$storage.get('Token')){
        this.$http({
            url: `${this.$storage.get('sessionTable')}/session`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code != 0) {
            router.push({ name: 'login' })
            }
        });
        }else{
            router.push({ name: 'login' })
        }
    },
    setToday(){
      const d = new Date()
      this.today = `${d.getFullYear()}-${String(d.getMonth()+1).padStart(2,'0')}-${String(d.getDate()).padStart(2,'0')}`
    },
    tickClock(){
      const d = new Date()
      this.clock = `${String(d.getHours()).padStart(2,'0')}:${String(d.getMinutes()).padStart(2,'0')}:${String(d.getSeconds()).padStart(2,'0')}`
    },
    getStats() {
      console.log('开始获取统计数据');
      this.$http({
        url: '/home/stats',
        method: 'get'
      }).then(({ data }) => {
        console.log('获取统计数据成功:', data);
        if (data.code === 0) {
          const statsData = data.data;
          this.stats[0].value = statsData.xueyuanCount.toString();
          this.stats[1].value = statsData.jiaolianCount.toString();
          this.stats[2].value = statsData.todayAppointmentCount.toString();
          this.stats[3].value = statsData.thisMonthExamCount.toString();
        }
      }).catch(error => {
        console.error('获取统计数据失败:', error);
        console.error('错误信息:', error.message);
        console.error('请求配置:', error.config);
      });
    },
    getActivity() {
      console.log('开始获取活跃数据');
      this.$http({
        url: '/home/activity',
        method: 'get'
      }).then(({ data }) => {
        console.log('获取活跃数据成功:', data);
        if (data.code === 0) {
          this.bars = data.data;
        }
      }).catch(error => {
        console.error('获取活跃数据失败:', error);
        console.error('错误信息:', error.message);
        console.error('请求配置:', error.config);
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.dash-wrap {
  padding: 20px 24px 40px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Microsoft YaHei", sans-serif;
  color: #2b3452;
  min-height: calc(100vh - 60px);
  background:
    radial-gradient(1000px 400px at 10% -10%, rgba(110,168,255,.18), transparent 60%),
    radial-gradient(800px 400px at 90% -20%, rgba(166,111,255,.15), transparent 60%),
    linear-gradient(180deg, #f6f9ff 0%, #ffffff 100%);
}

.hero {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 32px 36px;
  border-radius: 20px;
  background: linear-gradient(135deg, rgba(255,255,255,0.9) 0%, rgba(246,249,255,0.9) 100%);
  border: 1px solid rgba(230, 236, 250, 0.9);
  box-shadow: 0 10px 30px rgba(120, 140, 200, 0.08);
  overflow: hidden;

  .hero-left { position: relative; z-index: 2; max-width: 70%; }

  .hero-tag {
    display: inline-block;
    padding: 4px 12px;
    font-size: 12px;
    color: #6ea8ff;
    background: rgba(110, 168, 255, 0.1);
    border-radius: 999px;
    margin-bottom: 14px;
    letter-spacing: 1px;
  }
  .hero-title {
    font-size: 28px;
    font-weight: 700;
    margin: 0 0 8px 0;
    color: #1f2a44;
  }
  .grad-text {
    background: linear-gradient(135deg, #6ea8ff 0%, #a66fff 60%, #ff7b9a 100%);
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
  }
  .hero-desc {
    color: #7d8aa8;
    font-size: 14px;
    margin: 0 0 22px 0;
  }
  .hero-meta {
    display: flex;
    gap: 28px;
    flex-wrap: wrap;

    .meta-item {
      display: flex;
      flex-direction: column;
      .meta-label { font-size: 12px; color: #9ba6c4; margin-bottom: 4px; }
      .meta-value { font-size: 16px; font-weight: 600; color: #2b3452; }
    }
  }

  .hero-right {
    position: relative;
    width: 220px;
    height: 180px;

    .hero-illu {
      position: absolute;
      inset: 0;
      margin: auto;
      width: 180px;
      height: 180px;
      animation: spin 24s linear infinite;
    }

    .orb {
      position: absolute;
      border-radius: 50%;
      filter: blur(24px);
      opacity: 0.7;
    }
    .orb-a { width: 120px; height: 120px; background: #b8d5ff; top: -10px; right: 10px; animation: float 6s ease-in-out infinite; }
    .orb-b { width: 90px; height: 90px; background: #e0c2ff; bottom: -5px; right: 60px; animation: float 7s ease-in-out infinite reverse; }
    .orb-c { width: 70px; height: 70px; background: #ffc8d8; bottom: 30px; right: -10px; animation: float 5s ease-in-out infinite; }
  }
}
@keyframes spin { to { transform: rotate(360deg); } }
@keyframes float {
  0%,100% { transform: translateY(0); }
  50% { transform: translateY(-12px); }
}

.stat-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 18px;
  margin-top: 20px;
}

.stat-card {
  position: relative;
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 22px;
  background: #fff;
  border: 1px solid rgba(230, 236, 250, 0.9);
  border-radius: 18px;
  box-shadow: 0 8px 22px rgba(120, 140, 200, 0.06);
  overflow: hidden;
  transition: transform 0.25s ease, box-shadow 0.25s ease;

  &::before {
    content: "";
    position: absolute;
    top: -40px; right: -40px;
    width: 120px; height: 120px;
    background: linear-gradient(135deg, var(--c1), var(--c2));
    opacity: 0.08;
    border-radius: 50%;
    filter: blur(10px);
    transition: all 0.3s ease;
  }

  &:hover {
    transform: translateY(-3px);
    box-shadow: 0 14px 32px rgba(120, 140, 200, 0.12);
    &::before { opacity: 0.18; }
  }

  .stat-icon {
    width: 48px;
    height: 48px;
    border-radius: 14px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #fff;
    background: linear-gradient(135deg, var(--c1), var(--c2));
    box-shadow: 0 6px 16px rgba(0,0,0,0.08);
    flex-shrink: 0;
  }
  .stat-body { flex: 1; min-width: 0; }
  .stat-label { font-size: 13px; color: #8a96b5; margin-bottom: 6px; }
  .stat-value { font-size: 24px; font-weight: 700; color: #1f2a44; margin-bottom: 4px; }
  .stat-sub { font-size: 12px; display: flex; align-items: center; gap: 8px; }
  .trend.up { color: #22c58b; }
  .trend.down { color: #ff6b8a; }
  .stat-hint { color: #aab4ce; }
}

.panel-row {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 18px;
  margin-top: 18px;
}

.panel {
  background: #fff;
  border: 1px solid rgba(230, 236, 250, 0.9);
  border-radius: 18px;
  padding: 22px;
  box-shadow: 0 8px 22px rgba(120, 140, 200, 0.06);
}

.panel-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;

  .panel-title {
    font-size: 16px;
    font-weight: 600;
    color: #1f2a44;
  }
  .panel-chip {
    font-size: 11px;
    color: #22c58b;
    padding: 3px 10px;
    border-radius: 999px;
    background: rgba(34, 197, 139, 0.1);

    &::before {
      content: "";
      display: inline-block;
      width: 6px; height: 6px;
      background: #22c58b;
      border-radius: 50%;
      margin-right: 6px;
      vertical-align: middle;
      animation: pulse 1.5s ease-in-out infinite;
    }
  }
}
@keyframes pulse {
  0%,100% { opacity: 1; transform: scale(1); }
  50% { opacity: 0.5; transform: scale(1.4); }
}

.bar-chart {
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  height: 180px;
  padding: 0 6px;

  .bar-col {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;

    .bar {
      width: 60%;
      max-width: 36px;
      border-radius: 8px 8px 2px 2px;
      background: linear-gradient(180deg, #6ea8ff 0%, #a66fff 100%);
      box-shadow: 0 4px 12px rgba(110, 130, 255, 0.3);
      transition: transform 0.3s ease, filter 0.3s ease;
      animation: barRise 0.8s cubic-bezier(0.22, 1, 0.36, 1);
    }
    .bar:hover { transform: translateY(-4px); filter: brightness(1.08); }
    .bar-label {
      margin-top: 10px;
      font-size: 12px;
      color: #9ba6c4;
    }
  }
}
@keyframes barRise {
  from { transform: scaleY(0); transform-origin: bottom; opacity: 0; }
  to { transform: scaleY(1); opacity: 1; }
}

.quick-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12px;

  .quick-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 18px 10px;
    border-radius: 14px;
    background: linear-gradient(135deg, #f6f9ff 0%, #fbf6ff 100%);
    border: 1px solid rgba(230, 236, 250, 0.8);
    cursor: pointer;
    transition: all 0.25s ease;

    .quick-icon {
      width: 38px;
      height: 38px;
      border-radius: 10px;
      background: linear-gradient(135deg, #6ea8ff, #a66fff);
      color: #fff;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-bottom: 10px;
      box-shadow: 0 4px 12px rgba(110, 130, 255, 0.25);
    }
    .quick-text {
      font-size: 13px;
      color: #4a5677;
    }

    &:hover {
      transform: translateY(-2px);
      border-color: #c2d1f5;
      box-shadow: 0 8px 18px rgba(120, 140, 200, 0.12);
    }
  }
}

@media (max-width: 1100px) {
  .stat-grid { grid-template-columns: repeat(2, 1fr); }
  .panel-row { grid-template-columns: 1fr; }
}
@media (max-width: 640px) {
  .hero { flex-direction: column; align-items: flex-start; }
  .hero-right { display: none; }
  .stat-grid { grid-template-columns: 1fr; }
  .quick-grid { grid-template-columns: repeat(2, 1fr); }
}
</style>
