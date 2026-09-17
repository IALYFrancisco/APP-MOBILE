import { NativeModule, requireNativeModule } from 'expo';

declare class MobileTelephonyModule extends NativeModule<{}> {}

export default requireNativeModule<MobileTelephonyModule>('MobileTelephony');
