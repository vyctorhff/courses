import Link from "next/link";
import { Suspense } from "react";
import { Suspense1 } from "./components/suspense";
import { DelayService } from "@/app/services/delay.service";
import { HookCustom, HookEffect, HookRef } from "./components/hooks";

export default async function Page() {
    const prom2 = new DelayService().getStringWithDelay("hello suspense!");
    return (<>
        <h1>Advance</h1>
        <Link href="/">Back</Link>
        <hr/>

        <h2>Suspend + use</h2>
        <Suspense fallback={<p>Loading until suspense trigger...</p>}>
            <Suspense1 callBack={prom2}/>
        </Suspense>
        <hr/>

        <h2>Hook</h2>
        <HookEffect/>
        <HookCustom/>
        <HookRef/>
        <hr/>
    </>);
}