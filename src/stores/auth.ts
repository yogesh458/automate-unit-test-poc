import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import axios from '@/utils/axios'

export interface User {
  id?: number
  username?: string
  email?: string
  fullName?: string
  name?: string
}

type FieldErrors = Record<string, string>

function normalizeFieldErrors(errors: unknown): FieldErrors {
  if (!errors || typeof errors !== 'object') {
    return {}
  }

  return Object.entries(errors as Record<string, unknown>).reduce<FieldErrors>((fieldErrors, [field, value]) => {
    if (Array.isArray(value)) {
      fieldErrors[field] = String(value[0] ?? '')
    } else if (value) {
      fieldErrors[field] = String(value)
    }

    return fieldErrors
  }, {})
}

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User | null>(
    localStorage.getItem('username')
      ? { username: localStorage.getItem('username') || undefined }
      : null
  )
  const token = ref<string | null>(localStorage.getItem('token'))
  const isLoading = ref(false)
  const error = ref<string | null>(null)
  const fieldErrors = ref<FieldErrors>({})

  const isAuthenticated = computed(() => !!token.value)

  async function login(username: string, password: string) {
    isLoading.value = true
    error.value = null
    fieldErrors.value = {}
    
    try {
      const response = await axios.post('/auth/login', { username, password })
      const authToken = response.data.data.token as string
      token.value = authToken
      user.value = { username }
      localStorage.setItem('token', authToken)
      localStorage.setItem('username', username)
      axios.defaults.headers.common['Authorization'] = `Bearer ${authToken}`
      return true
    } catch (err: any) {
      fieldErrors.value = normalizeFieldErrors(err.response?.data?.errors)
      error.value = err.response?.data?.message || 'Login failed'
      return false
    } finally {
      isLoading.value = false
    }
  }

  async function register(username: string, password: string, email: string, fullName: string) {
    isLoading.value = true
    error.value = null
    fieldErrors.value = {}
    
    try {
      const response = await axios.post('/auth/register', {
        username,
        password,
        email,
        fullName,
      })
      const authToken = response.data.data.token as string
      token.value = authToken
      user.value = { username, email, fullName }
      localStorage.setItem('token', authToken)
      localStorage.setItem('username', username)
      axios.defaults.headers.common['Authorization'] = `Bearer ${authToken}`
      return true
    } catch (err: any) {
      fieldErrors.value = normalizeFieldErrors(err.response?.data?.errors)
      error.value = err.response?.data?.message || 'Registration failed'
      return false
    } finally {
      isLoading.value = false
    }
  }

  function logout() {
    user.value = null
    token.value = null
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    delete axios.defaults.headers.common['Authorization']
  }

  function clearErrors() {
    error.value = null
    fieldErrors.value = {}
  }

  return { user, token, isLoading, error, fieldErrors, isAuthenticated, login, register, logout, clearErrors }
})
