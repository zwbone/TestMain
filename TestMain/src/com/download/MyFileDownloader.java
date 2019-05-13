package com.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.log4j.Logger;

public class MyFileDownloader extends Thread {
	private static final Logger logger = Logger.getLogger(MyFileDownloader.class);

	private static final String TAG = "FileDownloader";
	public static final int BUFFER_SIZE = 1024;// 512
	public static final String DEFAULT_USER_AGENT = "MotoneMarket";

	/** Error Code */
	/** û���ҵ��ļ�������URL���� */
	public static final int E_URL_INVALID = -1;
	/** HTTP protocol ��Ч */
	public static final int E_PROTOCOL_INVALID = 0;
	/** network I/O error */
	public static final int E_IO_EXP = 1;
	/** ���������ļ�ʧ�� */
	public static final int E_FILE_NOTFOUND = 2;
	/** �ļ���ȫȨ�޴��� */
	public static final int E_FILE_SECURITY = 3;
	/** request ״̬�쳣 */
	public static final int E_ILLEGAL_STATE = 4;
	/** ׷����֤�� ���� */
	public static final int E_APPEND_VER_ERROR = 5;
	/** ���ز���� */
	public static final int E_DOWN_NOT_COMPLETE = 6;
	/** �ϵ�����������ϵ㷵�ش��� */
	public static final int E_RANGE_WRANG = 7;
	/** �ϵ�����������ϵ㷵�ش��� */
	public static final int E_CONNECT_TIMEOUT = 8;
	/** �洢�ռ䲻�㣬�޷������ļ� �� д���ļ� */
	public static final int E_NO_SPACE = 9;
	//////////////////////////////////////////////////////////////////////
	/** ͬ���� */
	private final Object mLock = new Object();
	/** ��ͣ��־��Ĭ��false,�������� */
	private boolean m_isPause = false;
	/** ֹͣ��־��Ĭ��trueֹͣδ��ʼtrue��false�������� */
	private boolean m_isStop = true;
	/** ȡ����־: Ĭ��falseδȡ����true:ȡ�� || false:δȡ�� */
	private boolean m_isCancel = false;
	/** �˳���ɱ�־: Ĭ��falseδ��ʼ��δ��ɣ�true����� || false:δ��� */
	private boolean m_isExit = false;

	//////////////////////////////////////////////////////////////////////
	/** �����ض���� ID */
	private int mId;
	/** ������Ŀ�� URL */
	private String mUrl;
	/** �ļ�ȫ�� */
	private String mFile;
	/** ��ʱ�ļ��� */
	private String mTFile;
	/** �ļ�����֤�ַ��� */
	private String mVerifyString = null;
	/** �ļ����ؼ����� */
	private OnDownloadListener mDownListener;

	//////////////////////////////////////////////////////////////////////
	private HttpURLConnection httpClient;
	/** д���ļ���λ�ã��ļ���appendλ�� */
	private long mPos = 0;
	/** �ļ��ܳ��� */
	private long mLength = -1;

	/** HTTP getInput */
	private InputStream streamEntry = null;
	/** File Stream */
	private FileOutputStream stream = null;

	//////////////////////////////////////////////////////////////////////
	public MyFileDownloader(int id, String url, String file, String verStr) {
		this.mId = id;
		this.mUrl = url;
		this.mFile = file;
		this.mTFile = file + ".info";
		this.mVerifyString = verStr;
	}

	/** �������ؼ����� */
	public synchronized void setOnDownloadListener(OnDownloadListener listener) {
		this.mDownListener = listener;
	}

	/** ���ؼ����� */
	public interface OnDownloadListener {
		void onFinish(int id);

		void onProgress(int id, int percent);

		void onError(int id, int errorCode);
	}

	/** ��ȡ������ID */
	public int getID() {
		return mId;
	}

	/** ������Դ */
	public void deleteFile() {
		File f = new File(mFile);

		if (f.exists()) {
			f.delete();
		}
	}

	/** ������ʱ�ļ� */
	public void deleteTFile() {
		File f = new File(mTFile);

		if (f.exists()) {
			f.delete();
		}
	}

	//////////////////////////////////////////////////////////////////////
	/** �̲߳��� */
	/** �����߳� */
	public synchronized void start() {

		super.start();
		logger.info("###_start get url = " + mUrl);
	}

	/** ����ѭ�� */
	public void run() {
		this.setPriority(MIN_PRIORITY);
		// ��ʼ������...
		m_isStop = false;
		/** ��ȡ�����ļ��������س��� */
		mPos = getLocalFileSize();
		/** ��ʼ�����磬����ȡ�����ļ����� */
		mLength = initHttp();
		if (mLength < 0) {
			close();
			return;
		}
		/** ��ʼ�����ļ� */
		boolean r = true;
		byte buf[] = new byte[BUFFER_SIZE];
		int numread = 0;
		int prePrec = 0;
		int CurPrec = 0;

		if (mPos < mLength) {
			/** ���� ��� �Խ� */
			try {
				/** ��ȡ��·������ */
				streamEntry = httpClient.getInputStream();
				/** װ�ε� FileOutputStream */
				File file = new File(mFile);
				if (!file.exists()) {
					file.createNewFile();
				}
				stream = new FileOutputStream(file, true);

			} catch (IllegalStateException e) {
				e.printStackTrace();
				doError(E_ILLEGAL_STATE);
				r = false;
			} catch (SecurityException e) {
				e.printStackTrace();
				doError(E_FILE_SECURITY);
				r = false;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				doError(E_FILE_NOTFOUND);
				r = false;
			} catch (IOException e) {
				e.printStackTrace();
				doError(E_NO_SPACE);
				r = false;
			}

			if (!r) {
				return;
			}

			/** ѭ��д�뻺���ļ� */
			if (streamEntry != null && stream != null) {
				while (true && !m_isStop) {
					/** �ȴ� */
					_wait();
					/** ���� */
					try {
						/** ��ȡ�� ���� buf */
						numread = streamEntry.read(buf);
					} catch (IOException e) {
						e.printStackTrace();
						doError(E_IO_EXP);
						r = false;
						return;
					}
					// ����50ms
					// try
					// {
					// Thread.sleep(50);
					// } catch (InterruptedException e1)
					// {
					// e1.printStackTrace();
					// }
					/**
					 * the number of bytes actually read or -1 if the end of the
					 * stream has been reached.
					 */
					if (numread < 0 || mPos == mLength) {
						break;
					}
					/** д�� �ļ� */
					try {
						stream.write(buf, 0, numread);
					} catch (IOException e) {
						e.printStackTrace();
						doError(E_NO_SPACE);
						r = false;
						return;
					}
					/** ���Ӽ�¼ */
					mPos += numread;
					/** ��������� */
					if (mDownListener != null) {
						CurPrec = (int) ((float) mPos / mLength * 100);
						if (CurPrec > prePrec) {
							try {
								mDownListener.onProgress(mId, CurPrec);
							} catch (Exception e) {
								e.printStackTrace();
								logger.error("---> unable exception occor! <----");
							}
							prePrec = CurPrec;
						}
					}
				}
			}
		}

		/** ���������� */
		if (mPos == mLength) {
			/** ׷����֤�ַ��� */
			boolean verable = true;
			if (mVerifyString != null) {
				byte[] bytes = mVerifyString.getBytes();
				try {
					if (stream != null) {
						stream.write(bytes, 0, bytes.length);
					}
					deleteTFile();
					logger.info("---> add verstring at apk : " + mVerifyString);
				} catch (IOException e) {
					e.printStackTrace();
					doError(E_NO_SPACE);
					verable = false;
					return;
				}
			}
			/** ������� */
			if (verable && mDownListener != null) {
				try {
					mDownListener.onFinish(mId);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("---> unable exception occor! <----");
				}
			}
		} else {
			/** ���ز���� */
			if (!m_isStop) {
				doError(E_DOWN_NOT_COMPLETE);
				deleteTFile();
			} else
			/** ��;��ͣ */
			{
				/** do nothing */
			}
		}

		/** �ر���Դ */
		close();
		return;
	}

	/////////////////////////////////////////////////////////////////////////////////////
	/** ��ȡ�����ļ����� */
	private long initHttp() {
		/** ����URI */
		URL url = null;
		try {
			url = new URL(mUrl);
		} catch (MalformedURLException e) {
			/** ��Ч��URL */
			logger.error("url = " + mUrl);
			doError(E_URL_INVALID);
			e.printStackTrace();
			return -1;
		}

		try {
			httpClient = (HttpURLConnection) url.openConnection();
			String property = "bytes=" + mPos + "-";
			httpClient.setRequestProperty("RANGE", property);

			/** ���ӳ�ʱ�Ͷ�ȡ��ʱ����5�� */
			httpClient.setConnectTimeout(5000);
			httpClient.setReadTimeout(5000);

			httpClient.connect();

			int code = httpClient.getResponseCode();
			/** code == 200 || code == 206 */
			if (code < 400) {
				String header = null;
				/** ��ȡ content-length */
				for (int i = 1;; i++) {
					header = httpClient.getHeaderFieldKey(i);
					if (header != null) {
						if (header.toLowerCase().equals("content-length")) {
							mLength = Integer.parseInt(httpClient.getHeaderField(header));
							break;
						}
					} else {
						break;
					}
				}
				/** ��ȡ range */
				for (int j = 1;; j++) {
					header = httpClient.getHeaderFieldKey(j);
					if (header != null) {
						if (header.toLowerCase().equals("content-range")) {
							String range = httpClient.getHeaderField(header);
							int i = "bytes ".length();

							if (i > range.length()) {
								// content-range ���ز��Ϸ�ʱ,���� ��֧�ֶϵ������� Ϊ0 ����
								break;
							}

							String str = null;
							try {
								str = range.substring(i, range.length());
							} catch (IndexOutOfBoundsException e) {
								// content-range ���ز��Ϸ�ʱ,���� ��֧�ֶϵ������� Ϊ0 ����
								e.printStackTrace();
								break;
							}

							String[] split = str.split("-");
							if (split != null && split.length == 2) {
								try {
									long start = Long.parseLong(split[0].trim());
									String[] len = split[1].split("/");
									mLength = Long.parseLong(len[1].trim());

									if (start == mPos) {
										/** �ϵ����� OK */
									} else {
										/** �ϵ����� Fail */
										doError(E_RANGE_WRANG);
										return -1;
									}
								} catch (Exception e) {
									// content-range ���ز��Ϸ�ʱ,���� ��֧�ֶϵ������� Ϊ0 ����
									e.printStackTrace();
									doError(E_RANGE_WRANG);
									break;
								}
							}
							break;
						}
					} else {
						break;
					}
				}
				/** ���������ļ����� */
				return mLength;
			} else
			/** ����� Range ��Χ���Ϸ� */
			if (code == 416) {
				doError(E_RANGE_WRANG);
			} else {
				/** ����ʧ�� */
				doError(E_URL_INVALID);
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			doError(E_CONNECT_TIMEOUT);
		} catch (IOException e) {
			e.printStackTrace();
			doError(E_IO_EXP);
		} catch (Exception e) {
			e.printStackTrace();
			doError(E_IO_EXP);
		}
		return -1;
	}

	/**
	 * ��ȡ�����ļ����� 
	 * @return
	 */
	private long getLocalFileSize() {
		File f = new File(mFile);
		if (f.exists()) {
			return f.length();
		} else {
			File tf = new File(mTFile);
			try {
				tf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	/** ���������룬��close */
	private void doError(int errorCode) {
		if (mDownListener != null) {
			try {
				mDownListener.onError(mId, errorCode);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("---> unable exception occor! <----");
			}
		}

		close();

		return;
	}

	/** �ر���Դ */
	private void close() {
		/** �ر������� */
		if (streamEntry != null) {
			try {
				streamEntry.close();
				streamEntry = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/** �ر������ */
		if (stream != null) {
			try {
				stream.flush();
				stream.getFD().sync();
				stream.close();
				stream = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/** �ر� HTTP Client */
		if (httpClient != null) {
			httpClient.disconnect();
			httpClient = null;
		}
		/** ������Դ */
		if (m_isCancel) {
			deleteFile();
			deleteTFile();
		}
		// �������ʱ ״̬
		// ��������Ϊδ��ʼ
		m_isStop = true;
		// ��־�߳̽���
		m_isExit = true;
	}

	//////////////////////////////////////////////////////////////////////
	/** �̵߳ȴ� */
	private void _wait() {
		/** ˯��50ms */
		try {
			Thread.sleep(20);// 200
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		/** �ȴ� �� */
		synchronized (mLock) {
			while (m_isPause) {
				try {
					mLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** ��ͣ�߳� */
	public synchronized void _pause() {
		m_isPause = true;
		logger.info("###_pause---->");
	}

	/** �ָ��߳� */
	public synchronized void _resume() {
		m_isPause = false;
		synchronized (mLock) {
			mLock.notifyAll();
		}
		logger.info("###_resume---->");
	}

	/** ֹͣ�߳� */
	public synchronized void _stop() {
		m_isStop = true;
		_resume();
		logger.info("###_stop---->");
	}

	/** ȡ���߳� */
	public void _cancel() {
		if (!m_isStop) {
			// ������...
			_stop();
			m_isCancel = true;
		} else {
			// δ��ʼ�������н���
			deleteFile();
			deleteTFile();
		}
	}

	/** �߳��Ƿ���� */
	public synchronized boolean isFinished() {
		return m_isExit;
	}
}

/*HTTPͷ��һ��ϵ�����ʱ���õ�Range��Content-Rangeʵ��ͷ��
Range�û�����ͷ�У�ָ����һ���ֽڵ�λ�ú����һ���ֽڵ�λ�ã��磨Range��200-300��
Content-Range������Ӧͷ

�������������ļ�: 
***********************************
GET  /test.rar  HTTP/1.1 
Connection:  close 
Host:  116.1.219.219 
Range:  bytes=0-100 
***********************************
Rangeͷ���������ʵ���һ�����߶���ӷ�Χ��Range��ֵΪ0��ʾ��һ���ֽڣ�Ҳ����Range�����ֽ����Ǵ�0��ʼ��
��ʾͷ500���ֽڣ�bytes=0-499
��ʾ�ڶ���500�ֽڣ�bytes=500-999
��ʾ���500���ֽڣ�bytes=-500
��ʾ500�ֽ��Ժ�ķ�Χ��bytes=500-
��һ�������һ���ֽڣ�bytes=0-0,-1
ͬʱָ��������Χ��bytes=500-600,601-999


һ��������Ӧ 
***********************************
HTTP/1.1 206 OK 
Content-Length:  801      
Content-Type:  application/octet-stream  
Content-Location: http://www.onlinedown.net/hj_index.htm
Content-Range:  bytes  0-100/2350 //2350:�ļ��ܴ�С 
Last-Modified: Mon, 16 Feb 2009 16:10:12 GMT
Accept-Ranges: bytes
ETag: "d67a4bc5190c91:512"
Server: Microsoft-IIS/6.0
Date: Wed, 18 Feb 2009 07:55:26 GMT
***********************************

ע�⣺����û��������к���range �������������Ӧ����Ϊ206��
206 - Partial Content �ͻ�������һ������Rangeͷ��GET���󣬷��������������HTTP 1.1�£���*/