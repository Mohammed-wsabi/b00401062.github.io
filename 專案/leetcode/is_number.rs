pub fn is_number(s: String) -> bool {
    let mut state = "init";
    for c in s.trim().chars() {
        state = match c {
            '+' | '-' => match state {
                "init" => "sign",
                "exp" => "exp_sign",
                _ => "dead",
            },
            '0' ... '9' => match state {
                "init" | "num" | "sign" => "num",
                "dot" | "frac" => "frac",
                "exp" | "exp_num" | "exp_sign" => "exp_num",
                _ => "dead",
            },
            '.' => match state {
                "init" | "sign" => "dot",
                "num" => "frac",
                _ => "dead",
            },
            'e' => match state {
                "num" | "frac" => "exp",
                _ => "dead"
            },
            _ => "dead",
        };
        if state == "dead" {
            return false;
        }
    }
    return ["num", "frac", "exp_num"].contains(&state);
}
