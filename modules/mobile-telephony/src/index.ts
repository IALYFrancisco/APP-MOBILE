// Reexport the native module. On web, it will be resolved to MobileTelephonyModule.web.ts
// and on native platforms to MobileTelephonyModule.ts
export { default } from './MobileTelephonyModule';
export * from './MobileTelephony.types';
