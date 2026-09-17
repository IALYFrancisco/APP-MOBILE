import { registerWebModule, NativeModule } from 'expo';

class MobileTelephonyModule extends NativeModule<{}> {}

export default registerWebModule(MobileTelephonyModule, 'MobileTelephonyModule');
