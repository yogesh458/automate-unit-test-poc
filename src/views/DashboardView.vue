<template>
  <div class="dashboard">
    <nav class="navbar">
      <h1>Dashboard</h1>
      <div class="user-info">
        <span>Welcome, {{ authStore.user?.fullName || authStore.user?.name || authStore.user?.username || 'User' }}</span>
        <button @click="handleLogout" class="logout-btn">Logout</button>
      </div>
    </nav>
    
    <div class="dashboard-content">
      <div class="stats-grid">
        <div class="stat-card">
          <h3>Total Users</h3>
          <p class="stat-number">1,234</p>
          <span class="stat-change positive">+12%</span>
        </div>
        <div class="stat-card">
          <h3>Revenue</h3>
          <p class="stat-number">$45,678</p>
          <span class="stat-change positive">+8%</span>
        </div>
        <div class="stat-card">
          <h3>Active Sessions</h3>
          <p class="stat-number">567</p>
          <span class="stat-change negative">-3%</span>
        </div>
        <div class="stat-card">
          <h3>Conversion Rate</h3>
          <p class="stat-number">23.5%</p>
          <span class="stat-change positive">+2.1%</span>
        </div>
      </div>

      <div class="recent-activity">
        <h3>Recent Activity</h3>
        <ul>
          <li v-for="i in 5" :key="i" class="activity-item">
            <span class="activity-time">{{ new Date().toLocaleTimeString() }}</span>
            <span class="activity-text">User action #{{ i }} performed</span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const authStore = useAuthStore()

function handleLogout() {
  authStore.logout()
  router.push('/login')
}
</script>

<style scoped>
.dashboard {
  min-height: 100vh;
  background: #f5f5f5;
}
.navbar {
  background: white;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}
.user-info {
  display: flex;
  align-items: center;
  gap: 1rem;
}
.logout-btn {
  padding: 0.5rem 1rem;
  background: #dc3545;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.dashboard-content {
  padding: 2rem;
}
.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2rem;
}
.stat-card {
  background: white;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}
.stat-card h3 {
  color: #666;
  font-size: 0.9rem;
  margin-bottom: 0.5rem;
}
.stat-number {
  font-size: 2rem;
  font-weight: bold;
  margin: 0.5rem 0;
}
.stat-change {
  font-size: 0.9rem;
  padding: 0.25rem 0.5rem;
  border-radius: 12px;
}
.stat-change.positive {
  background: #d4edda;
  color: #155724;
}
.stat-change.negative {
  background: #f8d7da;
  color: #721c24;
}
.recent-activity {
  background: white;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}
.activity-item {
  display: flex;
  justify-content: space-between;
  padding: 0.75rem 0;
  border-bottom: 1px solid #eee;
}
.activity-time {
  color: #666;
  font-size: 0.9rem;
}
</style>
