package com.jeecg.p3.weixin.vo.resp;

/**
 * 视频消息
 * @author Administrator
 *
 */
public class VideoMessageResp extends BaseMessageResp{
	// 视频
    private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video Video) {
		this.Video = Video;
	}
    
}
