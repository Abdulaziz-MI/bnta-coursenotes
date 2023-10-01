import { useState } from "react";
import Counter from "../components/Counter";

const SignupContainer = () => {

    const [archeryCount, setArcheryCount] = useState(0);
    const [zorbCount, setZorbCount] = useState(0);
    const [canoeCount, setCanoeCount] = useState(0);

    return(
        <>
            <h2>Activities</h2>
            <Counter title={"Archery"} count={archeryCount} onButtonClick={setArcheryCount}/>
            <Counter title={"Zorbing"} count={zorbCount} onButtonClick={setZorbCount}/>
            <Counter title={"Canoeing"} count={canoeCount} onButtonClick={setCanoeCount}/>
            <p>Total Sign-ups: {archeryCount + zorbCount + canoeCount}</p>
        </>
    )

}

export default SignupContainer;