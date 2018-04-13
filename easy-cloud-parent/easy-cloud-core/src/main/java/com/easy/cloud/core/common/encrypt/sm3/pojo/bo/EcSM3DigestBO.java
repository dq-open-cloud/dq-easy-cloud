package com.easy.cloud.core.common.encrypt.sm3.pojo.bo;


import com.easy.cloud.core.common.encrypt.sm3.utils.EcSM3Utils;

/**
 * 
 * <p>
 * SM3业务逻辑对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午2:21:07
 */
public class EcSM3DigestBO {
	/** SM3值的长度 */
	private static final int BYTE_LENGTH = 32;

	/** SM3分组长度 */
	private static final int BLOCK_LENGTH = 64;

	/** 缓冲区长度 */
	private static final int BUFFER_LENGTH = BLOCK_LENGTH * 1;

	/** 缓冲区 */
	private byte[] xBuf = new byte[BUFFER_LENGTH];

	/** 缓冲区偏移量 */
	private int xBufOff;

	/** 初始向量 */
	private byte[] v = EcSM3Utils.IV.clone();

	private int cntBlock = 0;

	public EcSM3DigestBO() {
		
	}
	
	public static EcSM3DigestBO newInstance(){
		return new EcSM3DigestBO();
	}
	
	public EcSM3DigestBO(EcSM3DigestBO dqSM3DigestDTO) {
		System.arraycopy(dqSM3DigestDTO.xBuf, 0, this.xBuf, 0, dqSM3DigestDTO.xBuf.length);
		this.xBufOff = dqSM3DigestDTO.xBufOff;
		System.arraycopy(dqSM3DigestDTO.v, 0, this.v, 0, dqSM3DigestDTO.v.length);
	}

	/**
	 * SM3结果输出
	 *
	 * @param out
	 *            保存SM3结构的缓冲区
	 * @param outOff
	 *            缓冲区偏移量
	 * @return 字节长度
	 */
	public int doFinal(byte[] out, int outOff) {
		byte[] tmp = doFinal();
		System.arraycopy(tmp, 0, out, 0, tmp.length);
		return BYTE_LENGTH;
	}

	/**
	 * 重置
	 */
	public void reset() {
		xBufOff = 0;
		cntBlock = 0;
		v = EcSM3Utils.IV.clone();
	}

	/**
	 * 明文输入
	 *
	 * @param in
	 *            明文输入缓冲区
	 * @param inOff
	 *            缓冲区偏移量
	 * @param len
	 *            明文长度
	 */
	public void update(byte[] in, int inOff, int len) {
		int partLen = BUFFER_LENGTH - xBufOff;
		int inputLen = len;
		int dPos = inOff;
		if (partLen < inputLen) {
			System.arraycopy(in, dPos, xBuf, xBufOff, partLen);
			inputLen -= partLen;
			dPos += partLen;
			doUpdate();
			while (inputLen > BUFFER_LENGTH) {
				System.arraycopy(in, dPos, xBuf, 0, BUFFER_LENGTH);
				inputLen -= BUFFER_LENGTH;
				dPos += BUFFER_LENGTH;
				doUpdate();
			}
		}

		System.arraycopy(in, dPos, xBuf, xBufOff, inputLen);
		xBufOff += inputLen;
	}

	/**
	 * 更新
	 */
	private void doUpdate() {
		byte[] byteArray = new byte[BLOCK_LENGTH];
		for (int i = 0; i < BUFFER_LENGTH; i += BLOCK_LENGTH) {
			System.arraycopy(xBuf, i, byteArray, 0, byteArray.length);
			doHash(byteArray);
		}
		xBufOff = 0;
	}

	/**
	 * 转16进制
	 * 
	 * @param byteArray
	 *            字节数组
	 */
	private void doHash(byte[] byteArray) {
		byte[] tmp = EcSM3Utils.cf(v, byteArray);
		System.arraycopy(tmp, 0, v, 0, v.length);
		cntBlock++;
	}

	private byte[] doFinal() {
		byte[] byteArray = new byte[BLOCK_LENGTH];
		byte[] buffer = new byte[xBufOff];
		System.arraycopy(xBuf, 0, buffer, 0, buffer.length);
		byte[] tmp = EcSM3Utils.padding(buffer, cntBlock);
		for (int i = 0; i < tmp.length; i += BLOCK_LENGTH) {
			System.arraycopy(tmp, i, byteArray, 0, byteArray.length);
			doHash(byteArray);
		}
		return v;
	}

	public void update(byte in) {
		byte[] buffer = new byte[] { in };
		update(buffer, 0, 1);
	}

	public int getDigestSize() {
		return BYTE_LENGTH;
	}

}