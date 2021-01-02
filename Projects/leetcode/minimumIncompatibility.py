from functools import lru_cache
from itertools import combinations
from typing import List


def minimumIncompatibility(nums: List[int], k: int) -> int:
    partition_len = len(nums) // k
    @lru_cache(maxsize=None)
    def recurse(nums):
        if not nums: return 0
        result = float('inf')
        for combo in combinations(nums, partition_len):
            if len(set(combo)) < partition_len: continue
            updated_nums = list(nums)
            for i in combo:
                updated_nums.remove(i)
            result = min(
                result,
                max(combo) - min(combo) + recurse(tuple(updated_nums))
            )
        return result
    result = recurse(tuple(nums))
    return result if result != float('inf') else -1
