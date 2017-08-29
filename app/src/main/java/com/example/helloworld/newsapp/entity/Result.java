package com.example.helloworld.newsapp.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/8/22.
 */

public class Result<T> {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-08-22","title":"她和李宇春同为超女后却经离婚丧女！而今依旧为罕见病奔走！","description":"娱姐来了","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-28146801.static/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406834&signature=F46RESXeYEIMluyjtEoqOgiSjFvjKRrgBMuD3K3Jjgj9026ABoAzggwK8Udp3byj1fgxf-YNexzWwEltaEN24nh96JdSJk0ee4FVveB4bI0="},{"ctime":"2017-08-22","title":"郭敬明真性侵男下属了吗？从小嘲笑故乡，向往上海的奢华，他的占有欲野蛮生长","description":"娱姐来了","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-28146801.static/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406834&signature=-zeSJwK8gIvHTAOrBQ1*ZqmjhehIc9ehLB1eSZJ7ZgZMwm6sI0RjKBfdrUejWj3ggUWiCXdC81IPZtGrhhy*6UksYOhFhnSVLADnviNqvk8="},{"ctime":"2017-08-22","title":"人活着，六个不能缺","description":"心态决定一切","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-28950733.static/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406834&signature=F2o1rFUyE6wVKeojgz8W0Pk-m8ls-tF9W4OxNjvauhqnBa0wnAEHSMAqo7-VgAp4pDqwz9B*1YLAh6KqJd6uAdmz14qTXvyNXbVHi*c3hhQ="},{"ctime":"2017-08-22","title":"心态好，一切都好","description":"心灵禅苑","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-36293210.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406834&signature=EKSqAS1fcKcef6l*-LZstK0xLC9vEa78qRRw87U5OvevmKnqMXUE3aSDRZ1xUG7u8ufLTqWv3uvExjM0DT2zlXOmtOBuiPtar23dLJtywck="},{"ctime":"2017-08-22","title":"别把我的忍让，当成应该","description":"心灵禅苑","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-36296984.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406833&signature=emdbkzXLphOObL8U6Ac*VdNuo9fPNOasKSNCMa3AnW-Ftvxv0*FmR7lC*SmWaI3zoehPZU*mpwhT7q0yrAcPqBGlKUjzL5eMDs4bZ9mog*s="},{"ctime":"2017-08-22","title":"再忙，也别冷落在乎你的人","description":"心灵禅苑","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-35379348.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406833&signature=UCMJW*LQPmqNWX95tCz4W6FDJo36u8p92Wfjk7KKfetIPu9miiFA7*zul4nQTyKdsf*-kUUBlYF82yG6M72Ld8h0TFHYrpFsdkjFx5mdxfU="},{"ctime":"2017-08-22","title":"人间最苦是爹娘","description":"心灵禅苑","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-36363888.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406833&signature=FUvXvv*EtpQXhlAcmVJDWunzho4OfVuVABuaqPAxT*qY6YZVumCsbC7RIS-bWsvTSZ6UZQi0vjXkgr7qXA4F9KveCbaTDpxO*M6ylsX7Fco="},{"ctime":"2017-08-22","title":"最现实的人际关系（句句点破）","description":"心灵禅苑","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-36497321.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406832&signature=pwzPJK8EplRTxFkJdPGFzBIkCyv2s3E6WsFJR1DL9ZIjBXEdqbO6ak4QP0PEA2Ga0HSAYeqEsG-lBFApeDg-LsJ6TgK8UA4iR*f89QACUqc="},{"ctime":"2017-08-22","title":"静的力量（写给浮躁的人）","description":"心灵禅苑","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-33264653.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406832&signature=xKhaCy-tbhRdEVGIBrf3QmAFpQTcgCyLmoqd94NqYeTkyYc*BnC0nC3W*8tJOKm753YaUv8DlpG2Uj1qIHkX2jH*3Aw8vZhg5kHlsOyWAsU="},{"ctime":"2017-08-22","title":"请告诉孩子：不读书，换来的是一生的底层！家长也读读。","description":"心灵禅苑","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-25921796.jpg/640","url":"https://mp.weixin.qq.com/s?src=16&ver=320&timestamp=1503406832&signature=j2TBpF4DhaVY3aQYuXTXSfkTajZVV3BENh-NZtthIL-IFmeQs9Tc8d9mf1yVx2NNUxTyJkdgNkMKNWD8h6QAENIWdyCPyhyt5JzX6GFa*0Y="}]
     */
    @SerializedName("code")
    private int code;
    @SerializedName("success")
    private String msg;
    @SerializedName("newslist")
    private List<T> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<T> newslist) {
        this.newslist = newslist;
    }
}
