pub fn max_profit(prices: Vec<i32>) -> i32 {
    let mut is_holding = false;
    let mut buy_price = 0;
    let mut profit = 0;
    for idx in 0 .. prices.len() {
        if is_holding && (idx == prices.len() - 1 || prices[idx] > prices[idx + 1]) {
            profit += prices[idx] - buy_price;
            is_holding = false;
        } else if !is_holding && idx < prices.len() - 1 && prices[idx] < prices[idx + 1] {
            buy_price = prices[idx];
            is_holding = true;
        }
    }
    return profit;
}
