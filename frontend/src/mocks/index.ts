import worker from './Browser'

const initMockAPI = async (): Promise<void> => {
  if (import.meta.env.VITE_ENV === 'development') {
    console.log('mockup start')
    worker.start()
  }
}
export default initMockAPI
