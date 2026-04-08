export class DelayService {

    getBooleanWithDelay(value: boolean): Promise<boolean> {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                resolve(value);
            }, 5000);
        });
    }

    getStringWithDelay(message: string): Promise<string> {
        return new Promise((resolve, _) => {
            setTimeout(() => {
               resolve(message);
            }, 5000);
        });
    }
}