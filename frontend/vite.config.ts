import react from '@vitejs/plugin-react-swc'
import { defineConfig } from 'vite'
import { VitePWA } from 'vite-plugin-pwa'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react(),
    VitePWA({
      registerType: 'autoUpdate',
      devOptions: {
        enabled: false,
      },
      manifest: {
        name: '코스프리',
        short_name: '코스프리',
        theme_color: '#ffffff',
        icons: [
          {
            src: 'pwa-64x64.png',
            sizes: '64x64',
            type: 'image/png',
          },
          {
            src: 'pwa-192x192.png',
            sizes: '192x192',
            type: 'image/png',
          },
          {
            src: 'pwa-512x512.png',
            sizes: '512x512',
            type: 'image/png',
            purpose: 'any',
          },
          {
            src: 'maskable-icon-512x512.png',
            sizes: '512x512',
            type: 'image/png',
            purpose: 'maskable',
          },
        ],
      },
    }),
  ],
  resolve: {
    alias: [
      { find: '@api', replacement: '/src/@core/api' },
      { find: '@hook', replacement: '/src/@core/hook' },
      { find: '@util', replacement: '/src/@core/util' },
      { find: '@type', replacement: '/src/@type' },
      { find: '@asset', replacement: '/src/asset' },
      { find: '@component', replacement: '/src/component' },
      { find: '@page', replacement: '/src/page' },
      { find: '@recoil', replacement: '/src/recoil' },
      { find: '@style', replacement: '/src/style' },
      { find: '@styled', replacement: '/src/styled' },
      { find: '@mocks', replacement: '/src/mocks' },
      { find: '@context', replacement: '/src/context' },
      { find: '@data', replacement: '/src/data' },
      { find: 'node_modules', replacement: '/node_modules' },
    ],
  },
  server: {
    proxy: {
      '/api': {
        target: 'https://j10a603.p.ssafy.io',
        changeOrigin: true,
        secure: false,
      },
    },
    watch: {
      usePolling: true,
    },
  },
})
